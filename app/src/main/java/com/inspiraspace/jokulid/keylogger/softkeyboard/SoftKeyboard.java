package com.inspiraspace.jokulid.keylogger.softkeyboard;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.MetaKeyKeyListener;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inspiraspace.jokulid.JokulidApplication;
import com.inspiraspace.jokulid.MainActivity;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdpAutoTexts;
import com.inspiraspace.jokulid.adapter.AdpAutocomplateAddress;
import com.inspiraspace.jokulid.adapter.AdpLVResultOngkir;
import com.inspiraspace.jokulid.adapter.AdpSpinnerChatapp;
import com.inspiraspace.jokulid.adapter.AdpSpinnerPayment;
import com.inspiraspace.jokulid.adapter.AdpSubcdtPendings;
import com.inspiraspace.jokulid.keylogger.Emoji.EmojiHelper.EmojiconGridView;
import com.inspiraspace.jokulid.keylogger.Emoji.EmojiHelper.EmojiconsPopup;
import com.inspiraspace.jokulid.keylogger.Emoji.Emojicon;
import com.inspiraspace.jokulid.model.preaddtransaction.Chatapp;
import com.inspiraspace.jokulid.model.preaddtransaction.Payment;
import com.inspiraspace.jokulid.model.rajaongkir.Item_Ongkir;
import com.inspiraspace.jokulid.model.searchsubdistrict.Datum;
import com.inspiraspace.jokulid.model.transactions.Response;
import com.inspiraspace.jokulid.network.main.PulseAutoText;
import com.inspiraspace.jokulid.network.main.PulseMainServer;
import com.inspiraspace.jokulid.presenter.GeneratorAutoTexts;
import com.inspiraspace.jokulid.presenter.GeneratorTransactions;
import com.inspiraspace.jokulid.presenter.newTransaction.OnViewAddTransaction;
import com.inspiraspace.jokulid.presenter.newTransaction.PAddTransaction;
import com.inspiraspace.jokulid.presenter.shippmentfare.OnViewShippmentfare;
import com.inspiraspace.jokulid.presenter.shippmentfare.PShippmentFare;
import com.inspiraspace.jokulid.subactivities.AddAutoTextActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Example of writing an soft_input soft_method for a soft keyboard.  This code is
 * focused on simplicity over completeness, so it should in no way be considered
 * to be a complete soft keyboard implementation.  Its purpose is to provide
 * a basic example for how you would get started writing an soft_input soft_method, to
 * be fleshed out as appropriate.
 */
public class SoftKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener, View.OnClickListener, View.OnFocusChangeListener, TextWatcher, PulseMainServer, PulseAutoText, OnViewShippmentfare, OnViewAddTransaction {

    static final boolean DEBUG = false;
    private Context mContext;

    /**
     * This boolean indicates the optional example code for performing
     * processing of hard keys in addition to regular text generation
     * from on-screen interaction.  It would be used for soft_input methods that
     * perform language translations (such as converting text entered on
     * a QWERTY keyboard to Chinese), but may not be used for soft_input methods
     * that are primarily intended to be used for on-screen text entry.
     */
    static final boolean PROCESS_HARD_KEYS = true;

    private InputMethodManager mInputMethodManager;
    private EmojiconsPopup emojiPopup;
    private LatinKeyboardView mInputView;
    private CandidateView mCandidateView;
    private CompletionInfo[] mCompletions;

    private StringBuilder mComposing = new StringBuilder();
    private boolean mPredictionOn;
    private boolean mCompletionOn;
    private int mLastDisplayWidth;
    private boolean mCapsLock;
    private long mLastShiftTime;
    private long mMetaState;

    private LatinKeyboard mSymbolsKeyboard;
    private LatinKeyboard mSymbolsShiftedKeyboard;
    private LatinKeyboard mQwertyKeyboard;
    private LatinKeyboard mNumberKeyboard;

    private LatinKeyboard mCurKeyboard;

    private String mWordSeparators;

    /*
    * start candidateview
    * */
    LinearLayout layout_subcdt_content;
    TextView tv_title_toobar_subcdt;

    LinearLayout layout_candidatebar_main;

    View layout_subcdt_createinvoice;
    View layout_subcdt_shippmentFee;
    View layout_subcdt_autotext;
    View layout_subcdt_pending;
    /*
    * end candidateview
    * */

    /*
    * start shippment fee
    * */
    TextInputEditText etItemWeight;
    AutoCompleteTextView etFrom, etDestination;
    ArrayList<Datum> arrSubdistrict;
    AdpAutocomplateAddress autoTextAdapter;

    ListView listOngkir;
    AdpLVResultOngkir adpResultOngkir;
    List<Item_Ongkir> resultOngkir = new ArrayList<>();

    Button btn_shippmentfee_copytoclipboard;
    Button btn_count_shippmentfee;
    private String idShippmentOrigin = null, idShippmentDestination = null, weightShippment = null;

    private PShippmentFare onPresentShippmentfare;
    /*
    * end shippment fee
    * */

    /*Pending*/
    List<Response> responseTransactionList = new ArrayList<>();
    AdpSubcdtPendings adpSubcdtPendings;
    ListView lv_subcdt_pending;
    GeneratorTransactions generatorTransactions;

        /*end Pending*/

    /*Autotext*/
    Button btn_subcdt_autotext_search;
    ListView lv_subcdt_autotext_search;


    FloatingActionButton btn_add_autotext;
    GeneratorAutoTexts generatorAutoTexts;
    List<com.inspiraspace.jokulid.model.autotext.Response> autotextList = new ArrayList<>();
    AdpAutoTexts adpAutoTexts;
    EditText edt_subcdt_autotext_search;
    /*End Autotext*/

    /*Make Invoice*/
    TextInputEditText edt_add_transaction_customername;
    TextInputEditText edt_add_transaction_customernohp;
    AutoCompleteTextView edt_add_transaction_customeraddress;
    TextInputEditText edt_add_transaction_transactionongkir;
    Spinner sp_add_transaction_bankaccount;
    Spinner sp_add_transaction_chatapp;
    TextInputEditText edt_add_transaction_item_qty;
    TextInputEditText edt_add_transaction_item_name;
    TextInputEditText edt_add_transaction_item_price;
    TextInputEditText edt_add_transaction_transactionnote;
    Button btn_add_transaction_done;

    PAddTransaction pAddTransaction;

    private AdpSpinnerChatapp adpSpinnerChatapp;
    private AdpSpinnerPayment adpSpinnerPayment;

    private ArrayList<Payment> dataPayments;
    private ArrayList<Chatapp> dataChatapps;
    /*End Make Invoice*/

    //    TODO : Put all editable view here
    private EditText[] getAllEditableField() {
        return new EditText[]{
                etItemWeight,
                etFrom,
                etDestination,
                /*editable make invoice*/
                edt_add_transaction_customername,
                edt_add_transaction_customernohp,
                edt_add_transaction_customeraddress,
                edt_add_transaction_transactionongkir,
                edt_add_transaction_item_qty,
                edt_add_transaction_item_name,
                edt_add_transaction_item_price,
                edt_add_transaction_transactionnote
        };
    }

    private void resetEditext(EditText[] edt) {
        for (EditText i : edt) {
            i.setText("");
            i.setError(null);
        }
    }

    /**
     * Main initialization of the soft_input soft_method component.  Be sure to call
     * to super class.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mWordSeparators = getResources().getString(R.string.word_separators);
    }

    /**
     * This is the point where you can do all of your UI initialization.  It
     * is called after creation and any configuration change.
     */
    @Override
    public void onInitializeInterface() {
        if (mQwertyKeyboard != null) {
            // Configuration changes can happen after the keyboard gets recreated,
            // so we need to be able to re-build the keyboards if the available
            // space has changed.
            int displayWidth = getMaxWidth();
            if (displayWidth == mLastDisplayWidth) return;
            mLastDisplayWidth = displayWidth;
        }
        mQwertyKeyboard = new LatinKeyboard(this, R.xml.soft_qwerty);
        mSymbolsKeyboard = new LatinKeyboard(this, R.xml.symbols);
        mSymbolsShiftedKeyboard = new LatinKeyboard(this, R.xml.symbols_shift);
    }

    /**
     * Called by the framework when your view for creating soft_input needs to
     * be generated.  This will be called the first time your soft_input soft_method
     * is displayed, and every time it needs to be re-created such as due to
     * a configuration change.
     */
    @Override
    public View onCreateInputView() {
        mInputView = (LatinKeyboardView) getLayoutInflater().inflate(
                R.layout.soft_input, null);
        mInputView.setOnKeyboardActionListener(this);
        setLatinKeyboard(mQwertyKeyboard);

        emojiPopup = new EmojiconsPopup(mInputView, this, true);
        emojiPopup.setOnEmojiconBackspaceClickedListener(new EmojiconsPopup.OnEmojiconBackspaceClickedListener() {
            @Override
            public void onEmojiconBackspaceClicked(View v) {
                KeyEvent event = new KeyEvent(
                        0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
                getCurrentInputConnection().sendKeyEvent(event);
            }
        });
        emojiPopup.setOnEmojiconClickedListener(new EmojiconGridView.OnEmojiconClickedListener() {
            @Override
            public void onEmojiconClicked(Emojicon emojicon) {
                getCurrentInputConnection().commitText(emojicon.getEmoji(), emojicon.getEmoji().length());

            }
        });
        emojiPopup.setOnEmojiButtonBackClickedListener(new EmojiconsPopup.OnEmojiconButtonBackClickedListener() {
            @Override
            public void onEmojiconButtonBackClickedListener(View v) {
                emojiPopup.dismiss();
            }
        });

        return mInputView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_toolbar_back_subcdt_content:
                showCandidateBar();
                break;

            case R.id.btn_cdt_createinvoice:
                showSubCreateInvoice();
                break;

            case R.id.btn_cdt_cekongkir:
                showSubShippmentfee();
                break;
            case R.id.btn_cdt_autotext:
                showSubAutotext();
                break;

            case R.id.btn_cdt_pending:
                showSubPending();
                break;
            case R.id.btn_cdt_dashboard:
                Intent dialogIntent = new Intent(this, MainActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);
                break;

            case R.id.fab_autotext:
                Intent i = new Intent(this, AddAutoTextActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                break;

            case R.id.btn_shippmentfee_copytoclipboard:
                onPresentShippmentfare.OnClickCopyOngkir(adpResultOngkir.getItemsOngkir_selected());
                break;

            case R.id.btn_count_shippmentfee:
                weightShippment = etItemWeight.getText().toString();
                onPresentShippmentfare.OnCount(weightShippment, idShippmentOrigin, idShippmentDestination);
                break;
            case R.id.btn_add_transaction_done:
                pAddTransaction.OnAddTransaction(
                        edt_add_transaction_customername.getText().toString(),
                        edt_add_transaction_customernohp.getText().toString(),
                        edt_add_transaction_customeraddress.getText().toString(),
                        edt_add_transaction_transactionnote.getText().toString(),
                        edt_add_transaction_transactionongkir.getText().toString(),
                        dataPayments.get(sp_add_transaction_bankaccount.getSelectedItemPosition()).getBankAccountBankId(),
                        dataChatapps.get(sp_add_transaction_chatapp.getSelectedItemPosition()).getId(),
                        edt_add_transaction_item_qty.getText().toString(),
                        edt_add_transaction_item_name.getText().toString(),
                        edt_add_transaction_item_price.getText().toString()
                );
                break;
            default:
                break;
        }

    }

    private void normalizeKeyboard() {
        showCandidateBar();
        resetEditext(getAllEditableField());
    }

    private void showCandidateBar() {
        layout_candidatebar_main.setVisibility(View.VISIBLE);
        layout_subcdt_content.setVisibility(View.GONE);
        layout_subcdt_createinvoice.setVisibility(View.GONE);
        layout_subcdt_shippmentFee.setVisibility(View.GONE);
        layout_subcdt_autotext.setVisibility(View.GONE);
        layout_subcdt_pending.setVisibility(View.GONE);
        tv_title_toobar_subcdt.setText(null);
    }

    private void showSubCreateInvoice() {
        layout_subcdt_createinvoice.setVisibility(View.VISIBLE);
        layout_subcdt_content.setVisibility(View.VISIBLE);

        layout_subcdt_shippmentFee.setVisibility(View.GONE);
        layout_subcdt_autotext.setVisibility(View.GONE);
        layout_subcdt_pending.setVisibility(View.GONE);

        layout_candidatebar_main.setVisibility(View.GONE);
        tv_title_toobar_subcdt.setText(getString(R.string.title_subcdt_toolbar_createinvoice));

        btn_add_transaction_done.setOnClickListener(this);

        edt_add_transaction_customername.setOnFocusChangeListener(this);
        edt_add_transaction_customernohp.setOnFocusChangeListener(this);
        edt_add_transaction_customeraddress.setOnFocusChangeListener(this);
        edt_add_transaction_transactionongkir.setOnFocusChangeListener(this);
        edt_add_transaction_item_qty.setOnFocusChangeListener(this);
        edt_add_transaction_item_name.setOnFocusChangeListener(this);
        edt_add_transaction_item_price.setOnFocusChangeListener(this);
        edt_add_transaction_transactionnote.setOnFocusChangeListener(this);

        sp_add_transaction_chatapp.setOnFocusChangeListener(this);

    }

    private void showSubShippmentfee() {
        layout_subcdt_shippmentFee.setVisibility(View.VISIBLE);
        layout_subcdt_content.setVisibility(View.VISIBLE);

        layout_subcdt_createinvoice.setVisibility(View.GONE);
        layout_subcdt_autotext.setVisibility(View.GONE);
        layout_subcdt_pending.setVisibility(View.GONE);

        layout_candidatebar_main.setVisibility(View.GONE);
        tv_title_toobar_subcdt.setText(getString(R.string.title_subcdt_toolbar_countshippmentfee));

        adpResultOngkir = new AdpLVResultOngkir(mContext, resultOngkir);
        listOngkir.setAdapter(adpResultOngkir);

        etFrom.setThreshold(3);
        etDestination.setThreshold(3);

        etItemWeight.setOnFocusChangeListener(this);
        etFrom.setOnFocusChangeListener(this);
        etDestination.setOnFocusChangeListener(this);

        etFrom.addTextChangedListener(this);
        etDestination.addTextChangedListener(this);

        etFrom.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        idShippmentOrigin = arrSubdistrict.get(position).getSubdistrictId();
                    }
                }
        );

        etDestination.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        idShippmentDestination = arrSubdistrict.get(position).getSubdistrictId();
                    }
                }
        );

        idShippmentOrigin = JokulidApplication.getInstance().getShippmentOrigin().get(JokulidApplication.KEY_SHIPPMENT_ORIGIN_ID);
        weightShippment = JokulidApplication.getInstance().getShippmentWeight();

        etItemWeight.setText(weightShippment);
        etFrom.setText(JokulidApplication.getInstance().getShippmentOrigin().get(JokulidApplication.KEY_SHIPPMENT_ORIGIN));

        etDestination.requestFocus();
        btn_count_shippmentfee.setOnClickListener(this);
        btn_shippmentfee_copytoclipboard.setOnClickListener(this);
    }

    public void searchSubdistrict(String keyword, AutoCompleteTextView field) {
        onPresentShippmentfare.OnSearchAddress(keyword, field);
    }

    private void showAutocomplateDropdown(AutoCompleteTextView field, ArrayList<Datum> listAddress) {
        autoTextAdapter = new AdpAutocomplateAddress(mContext, R.layout.item_subdistrict, listAddress);
        autoTextAdapter.setDropDownViewResource(R.layout.item_subdistrict);

        field.setAdapter(autoTextAdapter);
        if (field.getText().length() >= field.getThreshold() && !autoTextAdapter.isEmpty()) {
            field.showDropDown();
        }
    }

    private void showSubAutotext() {
        layout_subcdt_autotext.setVisibility(View.VISIBLE);
        layout_subcdt_content.setVisibility(View.VISIBLE);

        layout_subcdt_createinvoice.setVisibility(View.GONE);
        layout_subcdt_shippmentFee.setVisibility(View.GONE);
        layout_subcdt_pending.setVisibility(View.GONE);

        layout_candidatebar_main.setVisibility(View.GONE);
        tv_title_toobar_subcdt.setText(getString(R.string.title_subcdt_toolbar_autotext));
        generatorAutoTexts.getAutoText("");

    }

    //send text to current field keyboad
    public void sendTextToFieldText(String text) {
        getCurrentInputConnection().commitText(text, 0);
    }


    private void showSubPending() {
        layout_subcdt_pending.setVisibility(View.VISIBLE);
        layout_subcdt_content.setVisibility(View.VISIBLE);

        layout_subcdt_createinvoice.setVisibility(View.GONE);
        layout_subcdt_shippmentFee.setVisibility(View.GONE);
        layout_subcdt_autotext.setVisibility(View.GONE);

        layout_candidatebar_main.setVisibility(View.GONE);
        tv_title_toobar_subcdt.setText(getString(R.string.title_subcdt_toolbar_pending));

        generatorTransactions.getTransactios(0);

    }

    private void setLatinKeyboard(LatinKeyboard nextKeyboard) {
        final boolean shouldSupportLanguageSwitchKey =
                mInputMethodManager.shouldOfferSwitchingToNextInputMethod(getToken());
        nextKeyboard.setLanguageSwitchKeyVisibility(shouldSupportLanguageSwitchKey);
        mInputView.setKeyboard(nextKeyboard);
    }

    /**
     * Called by the framework when your view for showing candidates needs to
     * be generated, like {@link #onCreateInputView}.
     */
    @Override
    public View onCreateCandidatesView() {
        this.mContext = this;

        LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View wordBar = li.inflate(R.layout.candidatebar, null);

        ImageButton btntoolbar_back_subcdt_content = wordBar.findViewById(R.id.btn_toolbar_back_subcdt_content);
        ImageButton btn_cdt_makeinvoice = wordBar.findViewById(R.id.btn_cdt_createinvoice);
        ImageButton btn_cdt_shippmentfee = wordBar.findViewById(R.id.btn_cdt_cekongkir);
        ImageButton btn_cdt_autotext = wordBar.findViewById(R.id.btn_cdt_autotext);
        ImageButton btn_cdt_pending = wordBar.findViewById(R.id.btn_cdt_pending);
        ImageButton btn_cdt_dashboard = wordBar.findViewById(R.id.btn_cdt_dashboard);

        tv_title_toobar_subcdt = wordBar.findViewById(R.id.tv_title_toobar_subcdt);
        layout_candidatebar_main = wordBar.findViewById(R.id.ln_candidatebar_main);
        layout_subcdt_content = wordBar.findViewById(R.id.ln_subcdt_content);
        layout_subcdt_createinvoice = wordBar.findViewById(R.id.layout_subcdt_createinvoice);
        layout_subcdt_shippmentFee = wordBar.findViewById(R.id.layout_subcdt_shipmentfee);
        layout_subcdt_autotext = wordBar.findViewById(R.id.layout_subcdt_autotext);
        layout_subcdt_pending = wordBar.findViewById(R.id.layout_subcdt_pending);

        lv_subcdt_pending = wordBar.findViewById(R.id.lv_subcdt_pending);
        adpSubcdtPendings = new AdpSubcdtPendings(responseTransactionList, this);
        lv_subcdt_pending.setAdapter(adpSubcdtPendings);
        generatorTransactions = new GeneratorTransactions(this);
/*menu autotext */
        edt_subcdt_autotext_search = wordBar.findViewById(R.id.edt_subcdt_autotext_search);
        btn_subcdt_autotext_search = wordBar.findViewById(R.id.btn_subcdt_autotext_search);
        lv_subcdt_autotext_search = wordBar.findViewById(R.id.lv_subcdt_autotext_search);

        lv_subcdt_autotext_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendTextToFieldText(autotextList.get(position).getContent());
            }
        });
        btn_subcdt_autotext_search.setOnClickListener(this);
        adpAutoTexts = new AdpAutoTexts(this);
        btn_add_autotext = wordBar.findViewById(R.id.fab_autotext);
        btn_cdt_autotext.setOnClickListener(this);
        generatorAutoTexts = new GeneratorAutoTexts(this);
/*end menu autotext */
/*menu shippment fare*/
        onPresentShippmentfare = new PShippmentFare(mContext, this);
        etItemWeight = wordBar.findViewById(R.id.etItemWeight);
        etFrom = wordBar.findViewById(R.id.etFrom);
        etDestination = wordBar.findViewById(R.id.etDestination);
        btn_count_shippmentfee = wordBar.findViewById(R.id.btn_count_shippmentfee);
        listOngkir = wordBar.findViewById(R.id.listOngkir);
        btn_shippmentfee_copytoclipboard = wordBar.findViewById(R.id.btn_shippmentfee_copytoclipboard);
/*end menu shippment fare*/
/*menu Make Invoice*/
        edt_add_transaction_customername = wordBar.findViewById(R.id.edt_add_transaction_customername);
        edt_add_transaction_customernohp = wordBar.findViewById(R.id.edt_add_transaction_customernohp);
        edt_add_transaction_customeraddress = wordBar.findViewById(R.id.edt_add_transaction_customeraddress);
        edt_add_transaction_transactionongkir = wordBar.findViewById(R.id.edt_add_transaction_transactionongkir);
        sp_add_transaction_bankaccount = wordBar.findViewById(R.id.sp_add_transaction_bankaccount);
        sp_add_transaction_chatapp = wordBar.findViewById(R.id.sp_add_transaction_chatapp);
        edt_add_transaction_item_qty = wordBar.findViewById(R.id.edt_add_transaction_item_qty);
        edt_add_transaction_item_name = wordBar.findViewById(R.id.edt_add_transaction_item_name);
        edt_add_transaction_item_price = wordBar.findViewById(R.id.edt_add_transaction_item_price);
        edt_add_transaction_transactionnote = wordBar.findViewById(R.id.edt_add_transaction_transactionnote);
        btn_add_transaction_done = wordBar.findViewById(R.id.btn_add_transaction_done);

        pAddTransaction = new PAddTransaction(this);
/*end menu Make Invoice*/


        btntoolbar_back_subcdt_content.setOnClickListener(this);
        btn_cdt_makeinvoice.setOnClickListener(this);
        btn_cdt_shippmentfee.setOnClickListener(this);
        btn_cdt_autotext.setOnClickListener(this);
        btn_cdt_pending.setOnClickListener(this);
        btn_cdt_dashboard.setOnClickListener(this);

        mCandidateView = new CandidateView(this);
        mCandidateView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mCandidateView.setService(this);
        setCandidatesViewShown(true);
        return wordBar;
    }

    @Override
    public void onComputeInsets(InputMethodService.Insets
                                        outInsets) {
        super.onComputeInsets(outInsets);
        if (!isFullscreenMode()) {
            outInsets.contentTopInsets = outInsets.visibleTopInsets;
        }
    }

    /**
     * This is the main point where we do our initialization of the soft_input soft_method
     * to begin operating on an application.  At this point we have been
     * bound to the client, and are now receiving all of the detailed information
     * about the target of our edits.
     */
    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInput(attribute, restarting);

        // Reset our state.  We want to do this even if restarting, because
        // the underlying state of the text editor could have changed in any way.
        mComposing.setLength(0);
//        updateCandidates();

        if (!restarting) {
            // Clear shift states.
            mMetaState = 0;
        }

        mPredictionOn = false;
        mCompletionOn = false;
        mCompletions = null;

        // We are now going to initialize our state based on the type of
        // text being edited.
        switch (attribute.inputType & InputType.TYPE_MASK_CLASS) {
            case InputType.TYPE_CLASS_NUMBER:
            case InputType.TYPE_CLASS_DATETIME:
                // Numbers and dates default to the symbols keyboard, with
                // no extra features.
                mCurKeyboard = mSymbolsKeyboard;
                break;

            case InputType.TYPE_CLASS_PHONE:
                // Phones will also default to the symbols keyboard, though
                // often you will want to have a dedicated phone keyboard.
                mCurKeyboard = mSymbolsKeyboard;
                break;

            case InputType.TYPE_CLASS_TEXT:
                // This is general text editing.  We will default to the
                // normal alphabetic keyboard, and assume that we should
                // be doing predictive text (showing candidates as the
                // user types).
                mCurKeyboard = mQwertyKeyboard;
                mPredictionOn = true;

                // We now look for a few special variations of text that will
                // modify our behavior.
                int variation = attribute.inputType & InputType.TYPE_MASK_VARIATION;
                if (variation == InputType.TYPE_TEXT_VARIATION_PASSWORD ||
                        variation == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    // Do not display predictions / what the user is typing
                    // when they are entering a password.
                    mPredictionOn = false;
                }

                if (variation == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        || variation == InputType.TYPE_TEXT_VARIATION_URI
                        || variation == InputType.TYPE_TEXT_VARIATION_FILTER) {
                    // Our predictions are not useful for e-mail addresses
                    // or URIs.
                    mPredictionOn = false;
                }

                if ((attribute.inputType & InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE) != 0) {
                    // If this is an auto-complete text view, then our predictions
                    // will not be shown and instead we will allow the editor
                    // to supply their own.  We only show the editor's
                    // candidates when in fullscreen mode, otherwise relying
                    // own it displaying its own UI.
                    mPredictionOn = false;
                    mCompletionOn = isFullscreenMode();
                }

                // We also want to look at the current state of the editor
                // to decide whether our alphabetic keyboard should start out
                // shifted.
                updateShiftKeyState(attribute);
                break;

            default:
                // For all unknown soft_input types, default to the alphabetic
                // keyboard with no special features.
                mCurKeyboard = mQwertyKeyboard;
                updateShiftKeyState(attribute);
        }

        // Update the label on the enter key, depending on what the application
        // says it will do.
        mCurKeyboard.setImeOptions(getResources(), attribute.imeOptions);
    }

    /**
     * This is called when the user is done editing a field.  We can use
     * this to reset our state.
     */
    @Override
    public void onFinishInput() {
        super.onFinishInput();

        // Clear current composing text and candidates.
        mComposing.setLength(0);
//        updateCandidates();

        // We only hide the candidates window when finishing soft_input on
        // a particular editor, to avoid popping the underlying application
        // up and down if the user is entering text into the bottom of
        // its window.

//        This Line make me crazy so i comment, i not remove coz i need to memorize this WTF moment
//        setCandidatesViewShown(false);

        mCurKeyboard = mQwertyKeyboard;
        if (mInputView != null) {
            mInputView.closing();
        }
    }

    @Override
    public void onStartInputView(EditorInfo attribute, boolean restarting) {
        super.onStartInputView(attribute, restarting);
        // Apply the selected keyboard to the soft_input view.
        setLatinKeyboard(mCurKeyboard);
        mInputView.closing();
        final InputMethodSubtype subtype = mInputMethodManager.getCurrentInputMethodSubtype();
        mInputView.setSubtypeOnSpaceKey(subtype);
    }

    @Override
    public void onCurrentInputMethodSubtypeChanged(InputMethodSubtype subtype) {
        mInputView.setSubtypeOnSpaceKey(subtype);
    }

    /**
     * Deal with the editor reporting movement of its cursor.
     */
    @Override
    public void onUpdateSelection(int oldSelStart, int oldSelEnd,
                                  int newSelStart, int newSelEnd,
                                  int candidatesStart, int candidatesEnd) {
        super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd,
                candidatesStart, candidatesEnd);

        // If the current selection in the text view changes, we should
        // clear whatever candidate text we have.
        if (mComposing.length() > 0 && (newSelStart != candidatesEnd
                || newSelEnd != candidatesEnd)) {
            mComposing.setLength(0);
//            updateCandidates();
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                ic.finishComposingText();
            }
        }
    }

    /**
     * This tells us about completions that the editor has determined based
     * on the current text in it.  We want to use this in fullscreen mode
     * to show the completions ourself, since the editor can not be seen
     * in that situation.
     */
    @Override
    public void onDisplayCompletions(CompletionInfo[] completions) {
        if (mCompletionOn) {
            mCompletions = completions;
            if (completions == null) {
                setSuggestions(null, false, false);
                return;
            }

            List<String> stringList = new ArrayList<String>();
            for (int i = 0; i < completions.length; i++) {
                CompletionInfo ci = completions[i];
                if (ci != null) stringList.add(ci.getText().toString());
            }
            setSuggestions(stringList, true, true);
        }
    }

    /**
     * This translates incoming hard key events in to edit operations on an
     * InputConnection.  It is only needed when using the
     * PROCESS_HARD_KEYS option.
     */
    private boolean translateKeyDown(int keyCode, KeyEvent event) {
        mMetaState = MetaKeyKeyListener.handleKeyDown(mMetaState,
                keyCode, event);
        int c = event.getUnicodeChar(MetaKeyKeyListener.getMetaState(mMetaState));
        mMetaState = MetaKeyKeyListener.adjustMetaAfterKeypress(mMetaState);
        InputConnection ic = getCurrentInputConnection();
        if (c == 0 || ic == null) {
            return false;
        }

        boolean dead = false;

        if ((c & KeyCharacterMap.COMBINING_ACCENT) != 0) {
            dead = true;
            c = c & KeyCharacterMap.COMBINING_ACCENT_MASK;
        }

        if (mComposing.length() > 0) {
            char accent = mComposing.charAt(mComposing.length() - 1);
            int composed = KeyEvent.getDeadChar(accent, c);

            if (composed != 0) {
                c = composed;
                mComposing.setLength(mComposing.length() - 1);
            }
        }

        onKey(c, null);

        return true;
    }

    /**
     * Use this to monitor key events being delivered to the application.
     * We get first crack at them, and can either resume them or let them
     * continue to the app.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                // The InputMethodService already takes care of the back
                // key for us, to dismiss the soft_input soft_method if it is shown.
                // However, our keyboard could be showing a pop-up window
                // that back should dismiss, so we first allow it to do that.
                if (event.getRepeatCount() == 0 && mInputView != null) {
                    if (mInputView.handleBack()) {
                        return true;
                    }
                }
                break;

            case KeyEvent.KEYCODE_DEL:
                // Special handling of the delete key: if we currently are
                // composing text for the user, we want to modify that instead
                // of let the application to the delete itself.
                if (mComposing.length() > 0) {
                    onKey(Keyboard.KEYCODE_DELETE, null);
                    return true;
                }
                break;

            case KeyEvent.KEYCODE_ENTER:
                // Let the underlying text editor always handle these.
                return false;

            default:
                // For all other keys, if we want to do transformations on
                // text being entered with a hard keyboard, we need to process
                // it and do the appropriate action.
                if (PROCESS_HARD_KEYS) {
                    if (keyCode == KeyEvent.KEYCODE_SPACE
                            && (event.getMetaState() & KeyEvent.META_ALT_ON) != 0) {
                        // A silly example: in our soft_input soft_method, Alt+Space
                        // is a shortcut for 'android' in lower case.
                        InputConnection ic = getCurrentInputConnection();
                        if (ic != null) {
                            // First, tell the editor that it is no longer in the
                            // shift state, since we are consuming this.
                            ic.clearMetaKeyStates(KeyEvent.META_ALT_ON);
                            keyDownUp(KeyEvent.KEYCODE_A);
                            keyDownUp(KeyEvent.KEYCODE_N);
                            keyDownUp(KeyEvent.KEYCODE_D);
                            keyDownUp(KeyEvent.KEYCODE_R);
                            keyDownUp(KeyEvent.KEYCODE_O);
                            keyDownUp(KeyEvent.KEYCODE_I);
                            keyDownUp(KeyEvent.KEYCODE_D);
                            // And we consume this event.
                            return true;
                        }
                    }
                    if (mPredictionOn && translateKeyDown(keyCode, event)) {
                        return true;
                    }
                }
        }
        /*
        * reset all view
        * */
        normalizeKeyboard();

        return super.onKeyDown(keyCode, event);
    }

    /**
     * Use this to monitor key events being delivered to the application.
     * We get first crack at them, and can either resume them or let them
     * continue to the app.
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // If we want to do transformations on text being entered with a hard
        // keyboard, we need to process the up events to update the meta key
        // state we are tracking.
        if (PROCESS_HARD_KEYS) {
            if (mPredictionOn) {
                mMetaState = MetaKeyKeyListener.handleKeyUp(mMetaState,
                        keyCode, event);
            }
        }

        return super.onKeyUp(keyCode, event);
    }

    /**
     * Helper function to commit any text being composed in to the editor.
     */
    private void commitTyped(InputConnection inputConnection) {
        if (mComposing.length() > 0) {
            inputConnection.commitText(mComposing, mComposing.length());
            mComposing.setLength(0);
//            updateCandidates();
        }
    }


    /**
     * Helper to update the shift state of our keyboard based on the initial
     * editor state.
     */
    private void updateShiftKeyState(EditorInfo attr) {
        if (attr != null
                && mInputView != null && mQwertyKeyboard == mInputView.getKeyboard()) {
            int caps = 0;
            EditorInfo ei = getCurrentInputEditorInfo();
            if (ei != null && ei.inputType != InputType.TYPE_NULL) {
                caps = getCurrentInputConnection().getCursorCapsMode(attr.inputType);
            }
            mInputView.setShifted(mCapsLock || caps != 0);
        }
    }

    /**
     * Helper to determine if a given character code is alphabetic.
     */
    private boolean isAlphabet(int code) {
        if (Character.isLetter(code)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper to send a key down / key up pair to the current editor.
     */
    private void keyDownUp(int keyEventCode) {
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_DOWN, keyEventCode));
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_UP, keyEventCode));
    }

    /**
     * Helper to send a character to the editor as raw key events.
     */
    private void sendKey(int keyCode) {
        switch (keyCode) {
            case '\n':
                keyDownUp(KeyEvent.KEYCODE_ENTER);
                break;
            default:
                if (keyCode >= '0' && keyCode <= '9') {
                    keyDownUp(keyCode - '0' + KeyEvent.KEYCODE_0);
                } else {
                    getCurrentInputConnection().commitText(String.valueOf((char) keyCode), 1);
                }
                break;
        }
    }

    // Implementation of KeyboardViewListener

    public void onKey(int primaryCode, int[] keyCodes) {
        if (etItemWeight.isFocused()) {
            onInputCustomKeyboard(primaryCode, etItemWeight);
        } else if (etDestination.isFocused()) {
            onInputCustomKeyboard(primaryCode, etDestination);
        } else if (etFrom.isFocused()) {
            onInputCustomKeyboard(primaryCode, etFrom);
        } else if (edt_add_transaction_customername.isFocused()) {
            onInputCustomKeyboard(primaryCode, edt_add_transaction_customername);
        } else if (edt_add_transaction_customernohp.isFocused()) {
            onInputCustomKeyboard(primaryCode, edt_add_transaction_customernohp);
        } else if (edt_add_transaction_customeraddress.isFocused()) {
            onInputCustomKeyboard(primaryCode, edt_add_transaction_customeraddress);
        } else if (edt_add_transaction_transactionongkir.isFocused()) {
            onInputCustomKeyboard(primaryCode, edt_add_transaction_transactionongkir);
        } else if (edt_add_transaction_item_qty.isFocused()) {
            onInputCustomKeyboard(primaryCode, edt_add_transaction_item_qty);
        } else if (edt_add_transaction_item_name.isFocused()) {
            onInputCustomKeyboard(primaryCode, edt_add_transaction_item_name);
        } else if (edt_add_transaction_item_price.isFocused()) {
            onInputCustomKeyboard(primaryCode, edt_add_transaction_item_price);
        } else if (edt_add_transaction_transactionnote.isFocused()) {
            onInputCustomKeyboard(primaryCode, edt_add_transaction_transactionnote);
        } else {
            if (isWordSeparator(primaryCode)) {
                // Handle separator
                if (mComposing.length() > 0) {
                    commitTyped(getCurrentInputConnection());
                }
                sendKey(primaryCode);
                updateShiftKeyState(getCurrentInputEditorInfo());
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) {
                handleBackspace();
            } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
                handleShift();
            } else if (primaryCode == Keyboard.KEYCODE_CANCEL) {
                handleClose();
                return;
            } else if (primaryCode == LatinKeyboardView.KEYCODE_LANGUAGE_SWITCH) {
                handleLanguageSwitch();
                return;
            } else if (primaryCode == LatinKeyboardView.KEYCODE_OPTIONS) {
                // Show a menu or somethin'
            } else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE
                    && mInputView != null) {
                Keyboard current = mInputView.getKeyboard();
                if (current == mSymbolsKeyboard || current == mSymbolsShiftedKeyboard) {
                    setLatinKeyboard(mQwertyKeyboard);
                } else {
                    setLatinKeyboard(mSymbolsKeyboard);
                    mSymbolsKeyboard.setShifted(false);
                }
            } else if (primaryCode == 146) {
                this.setBackDisposition(BACK_DISPOSITION_WILL_NOT_DISMISS);
                emojiPopup.showAtBottom();

            } else {
                handleCharacter(primaryCode, keyCodes);
            }
        }
    }

    public void onInputCustomKeyboard(int primaryCode, EditText pInputText) {
        if (pInputText.isFocused()) {
            Editable edTextInput = pInputText.getText();
            int start = pInputText.getSelectionStart();
            char code = (char) primaryCode;
            if (primaryCode == Keyboard.KEYCODE_DELETE) {
                if (edTextInput != null && start > 0)
                    edTextInput.delete(start - 1, start);
            } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
                handleShift();
            } else if (primaryCode == Keyboard.KEYCODE_CANCEL) {
                handleClose();
                return;
            } else if (primaryCode == LatinKeyboardView.KEYCODE_LANGUAGE_SWITCH) {
                handleLanguageSwitch();
                return;
            } else if (primaryCode == LatinKeyboardView.KEYCODE_OPTIONS) {
                // Show a menu or somethin'
            } else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE
                    && mInputView != null) {
                Keyboard current = mInputView.getKeyboard();
                if (current == mSymbolsKeyboard || current == mSymbolsShiftedKeyboard) {
                    setLatinKeyboard(mQwertyKeyboard);
                } else {
                    setLatinKeyboard(mSymbolsKeyboard);
                    mSymbolsKeyboard.setShifted(false);
                }

            } else if (primaryCode == 146) {
                this.setBackDisposition(BACK_DISPOSITION_WILL_NOT_DISMISS);
                emojiPopup.showAtBottom();
            } else {
                edTextInput.insert(start, Character.toString((char) code));
            }

        }
    }

    @Override
    public void onText(CharSequence text) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        ic.beginBatchEdit();
        if (mComposing.length() > 0) {
            commitTyped(ic);
        }
        ic.commitText(text, 0);
        ic.endBatchEdit();
        updateShiftKeyState(getCurrentInputEditorInfo());
    }

    /**
     * Update the list of available candidates from the current composing
     * text.  This will need to be filled in by however you are determining
     * candidates.
     */
    /*private void updateCandidates() {
        if (!mCompletionOn) {
            if (mComposing.length() > 0) {
                ArrayList<String> list = new ArrayList<>();
                list.add(mComposing.toString());
                setSuggestions(list, true, true);
            } else {
                setSuggestions(null, false, false);
            }
        }
    }*/
    public void setSuggestions(List<String> suggestions, boolean completions,
                               boolean typedWordValid) {
        if (suggestions != null && suggestions.size() > 0) {
            setCandidatesViewShown(true);
        } else if (isExtractViewShown()) {
            setCandidatesViewShown(true);
        }
        if (mCandidateView != null) {
//            mCandidateView.setSuggestions(suggestions, completions, typedWordValid);
        }
    }

    private void handleBackspace() {
        final int length = mComposing.length();
        if (length > 1) {
            mComposing.delete(length - 1, length);
            getCurrentInputConnection().setComposingText(mComposing, 1);
//            updateCandidates();
        } else if (length > 0) {
            mComposing.setLength(0);
            getCurrentInputConnection().commitText("", 0);
//            updateCandidates();
        } else {
            keyDownUp(KeyEvent.KEYCODE_DEL);
        }
        updateShiftKeyState(getCurrentInputEditorInfo());
    }

    private void handleShift() {
        if (mInputView == null) {
            return;
        }

        Keyboard currentKeyboard = mInputView.getKeyboard();
        if (mQwertyKeyboard == currentKeyboard) {
            // Alphabet keyboard
            checkToggleCapsLock();
            mInputView.setShifted(mCapsLock || !mInputView.isShifted());
        } else if (currentKeyboard == mSymbolsKeyboard) {
            mSymbolsKeyboard.setShifted(true);
            setLatinKeyboard(mSymbolsShiftedKeyboard);
            mSymbolsShiftedKeyboard.setShifted(true);
        } else if (currentKeyboard == mSymbolsShiftedKeyboard) {
            mSymbolsShiftedKeyboard.setShifted(false);
            setLatinKeyboard(mSymbolsKeyboard);
            mSymbolsKeyboard.setShifted(false);
        }
    }

    private void handleCharacter(int primaryCode, int[] keyCodes) {
        if (isInputViewShown()) {
            if (mInputView.isShifted()) {
                primaryCode = Character.toUpperCase(primaryCode);
            }
        }
        if (isAlphabet(primaryCode) && mPredictionOn) {
            mComposing.append((char) primaryCode);
            getCurrentInputConnection().setComposingText(mComposing, 1);
            updateShiftKeyState(getCurrentInputEditorInfo());
//            updateCandidates();
        } else {
            getCurrentInputConnection().commitText(
                    String.valueOf((char) primaryCode), 1);
        }
    }

    private void handleClose() {
        commitTyped(getCurrentInputConnection());
        requestHideSelf(0);
        mInputView.closing();
    }

    private IBinder getToken() {
        final Dialog dialog = getWindow();
        if (dialog == null) {
            return null;
        }
        final Window window = dialog.getWindow();
        if (window == null) {
            return null;
        }
        return window.getAttributes().token;
    }

    private void handleLanguageSwitch() {
        mInputMethodManager.switchToNextInputMethod(getToken(), false /* onlyCurrentIme */);
    }

    private void checkToggleCapsLock() {
        long now = System.currentTimeMillis();
        if (mLastShiftTime + 800 > now) {
            mCapsLock = !mCapsLock;
            mLastShiftTime = 0;
        } else {
            mLastShiftTime = now;
        }
    }

    private String getWordSeparators() {
        return mWordSeparators;
    }

    public boolean isWordSeparator(int code) {
        String separators = getWordSeparators();
        return separators.contains(String.valueOf((char) code));
    }

    public void pickDefaultCandidate() {
        pickSuggestionManually(0);
    }

    public void pickSuggestionManually(int index) {
        if (mCompletionOn && mCompletions != null && index >= 0
                && index < mCompletions.length) {
            CompletionInfo ci = mCompletions[index];
            getCurrentInputConnection().commitCompletion(ci);
            if (mCandidateView != null) {
//                mCandidateView.clear();
            }
            updateShiftKeyState(getCurrentInputEditorInfo());
        } else if (mComposing.length() > 0) {
            // If we were generating candidate suggestions for the current
            // text, we would commit one of them here.  But for this sample,
            // we will just commit the current text.
            commitTyped(getCurrentInputConnection());
        }
    }

    public void swipeRight() {
        if (mCompletionOn) {
            pickDefaultCandidate();
        }
    }

    public void swipeLeft() {
        handleBackspace();
    }

    public void swipeDown() {
        handleClose();
    }

    public void swipeUp() {
    }

    public void onPress(int primaryCode) {
    }

    public void onRelease(int primaryCode) {
    }

/*start TextWatcher*/

    @Override
    public void afterTextChanged(Editable s) {
        if (etFrom.isFocused()) {
            idShippmentOrigin = null;
            if (s.length() >= etFrom.getThreshold()) {
                searchSubdistrict(etFrom.getText().toString(), etFrom);
            }
        } else if (etDestination.isFocused()) {
            idShippmentDestination = null;
            if (s.length() >= etDestination.getThreshold()) {
                searchSubdistrict(etDestination.getText().toString(), etDestination);
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
/*end TextWatcher*/

    /*start View.OnFocusChangeListener*/
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        EditorInfo editorInfo = getCurrentInputEditorInfo();
        Log.d("onFocusChange field", "onFocusChange: " + editorInfo.inputType);

        if (etItemWeight.isFocused()) {
            if (mInputView.getVisibility() == View.GONE) {
                mInputView.setVisibility(View.VISIBLE);
            }

        } else if (etFrom.isFocused()) {
            if (mInputView.getVisibility() == View.GONE) {
                mInputView.setVisibility(View.VISIBLE);
            }

        } else if (etDestination.isFocused()) {
            if (mInputView.getVisibility() == View.GONE) {
                mInputView.setVisibility(View.VISIBLE);
            }
        }
    }
            /*end View.OnFocusChangeListener*/

    @Override
    public void onSuccessGetTransactions(List<Response> transaction) {
        adpSubcdtPendings.swapLogs(transaction);
    }

    @Override
    public void onFailOccureTransactions(String errmsg) {

    }

    @Override
    public void onSuccess(List<com.inspiraspace.jokulid.model.autotext.Response> response) {
        autotextList = response;
        adpAutoTexts.swapLogs(autotextList);
    }

    @Override
    public void onError(String msgerror) {

    }

    @Override
    public void OnSuccessFindingsAddress(ArrayList<Datum> datumListAddress, AutoCompleteTextView field) {
        arrSubdistrict = datumListAddress;
        showAutocomplateDropdown(field, datumListAddress);
    }

    @Override
    public void OnSuccessShippmentfare(List<Item_Ongkir> resultListOngkir) {
        listOngkir.setVisibility(View.VISIBLE);
        this.resultOngkir = resultListOngkir;
        adpResultOngkir.swipeRefresh(resultOngkir);
        adpResultOngkir.notifyDataSetChanged();
        if (resultListOngkir.size() > 0)
            btn_shippmentfee_copytoclipboard.setVisibility(View.VISIBLE);
    }

    @Override
    public void OnSuccessLoadPaymentChattapp(ArrayList<Payment> dataPayments, ArrayList<Chatapp> dataChatapps) {
        this.dataChatapps = dataChatapps;
        this.dataPayments = dataPayments;

        adpSpinnerChatapp = new AdpSpinnerChatapp(this, R.id.tv_item_chatapp, dataChatapps);
        sp_add_transaction_chatapp.setAdapter(adpSpinnerChatapp);

        adpSpinnerPayment = new AdpSpinnerPayment(this, R.id.tv_item_payment, dataPayments);
        sp_add_transaction_bankaccount.setAdapter(adpSpinnerPayment);
    }

    @Override
    public void OnSuccessAddTransaction() {
        Toast.makeText(this, "New Pending Transaction Added", Toast.LENGTH_SHORT).show();
        normalizeKeyboard();
    }

    @Override
    public void OnErrorAddTransaction(String message) {
        System.out.println(message);
        Toast.makeText(this, "Fail Added Transaction", Toast.LENGTH_SHORT).show();
    }
}
