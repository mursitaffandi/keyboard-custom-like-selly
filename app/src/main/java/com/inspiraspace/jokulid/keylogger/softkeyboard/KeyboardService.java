package com.inspiraspace.jokulid.keylogger.softkeyboard;

/**
 * Created by mursitaffandi on 5/2/18.
 */

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
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
import com.inspiraspace.jokulid.utils.IMEUtil;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by arf on 11/22/17.
 */

public class KeyboardService extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener, View.OnFocusChangeListener, TextWatcher, View.OnClickListener, OnViewAddTransaction, PulseMainServer, OnViewShippmentfare, PulseAutoText {

    private static int KEYBOARD_MODE_QWERTY = R.integer.qwerty;
    private static int KEYBOARD_MODE_NUMERIC = R.integer.numeric;
    private static int KEYBOARD_MODE_SYMBOL = R.integer.symbol;
    private static int KEYBOARD_MODE_NUMBER_ONLY = R.integer.number_only;
    //Emoji
    EmojiconsPopup emojiPopup;
    //Input to flow

    private KeyboardView kv;
    private Keyboard keyboard;
    private InputMethodManager imm;
    private View customLayoutKeyboard;
    private int shiftState = 0;

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

    private void normalizeKeyboard() {
        showCandidateBar();
        resetEditext(getAllEditableField());
    }

    //send text to current field keyboad
    public void sendTextToFieldText(String text) {
        getCurrentInputConnection().commitText(text, 0);
    }


    public void searchSubdistrict(String keyword, AutoCompleteTextView field) {
        onPresentShippmentfare.OnSearchAddress(keyword, field);
    }

    private void showAutocomplateDropdown(AutoCompleteTextView field, ArrayList<Datum> listAddress) {
        autoTextAdapter = new AdpAutocomplateAddress(this, R.layout.item_subdistrict, listAddress);
        autoTextAdapter.setDropDownViewResource(R.layout.item_subdistrict);

        field.setAdapter(autoTextAdapter);
        if (field.getText().length() >= field.getThreshold() && !autoTextAdapter.isEmpty()) {
            field.showDropDown();
        }
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

        adpResultOngkir = new AdpLVResultOngkir(this, resultOngkir);
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

    private void showSubPending() {
        layout_subcdt_pending.setVisibility(View.VISIBLE);
        layout_subcdt_content.setVisibility(View.VISIBLE);

        layout_subcdt_createinvoice.setVisibility(View.GONE);
        layout_subcdt_shippmentFee.setVisibility(View.GONE);
        layout_subcdt_autotext.setVisibility(View.GONE);

        layout_candidatebar_main.setVisibility(View.GONE);
        tv_title_toobar_subcdt.setText(getString(R.string.title_subcdt_toolbar_pending));

        generatorTransactions.getTransaction(0);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        customLayoutKeyboard = getLayoutInflater().inflate(R.layout.custom_keyboard, null);
        kv = customLayoutKeyboard.findViewById(R.id.keyboard);
        keyboard = new Keyboard(this, R.xml.qwerty, KEYBOARD_MODE_QWERTY);

    }

    @Override
    public View onCreateInputView() {

        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        kv.setPreviewEnabled(false);
        View view = getLayoutInflater().inflate(R.layout.keyboard_key_popup, null);
        kv.setPopupParent(view);

        setKeyboardComponent();

        //emoji handler
        emojiPopup = new EmojiconsPopup(customLayoutKeyboard, this, true);
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
        ImageButton btntoolbar_back_subcdt_content = customLayoutKeyboard.findViewById(R.id.btn_toolbar_back_subcdt_content);
        ImageButton btn_cdt_makeinvoice = customLayoutKeyboard.findViewById(R.id.btn_cdt_createinvoice);
        ImageButton btn_cdt_shippmentfee = customLayoutKeyboard.findViewById(R.id.btn_cdt_cekongkir);
        ImageButton btn_cdt_autotext = customLayoutKeyboard.findViewById(R.id.btn_cdt_autotext);
        ImageButton btn_cdt_pending = customLayoutKeyboard.findViewById(R.id.btn_cdt_pending);
        ImageButton btn_cdt_dashboard = customLayoutKeyboard.findViewById(R.id.btn_cdt_dashboard);

        tv_title_toobar_subcdt = customLayoutKeyboard.findViewById(R.id.tv_title_toobar_subcdt);
        layout_candidatebar_main = customLayoutKeyboard.findViewById(R.id.ln_candidatebar_main);
        layout_subcdt_content = customLayoutKeyboard.findViewById(R.id.ln_subcdt_content);
        layout_subcdt_createinvoice = customLayoutKeyboard.findViewById(R.id.layout_subcdt_createinvoice);
        layout_subcdt_shippmentFee = customLayoutKeyboard.findViewById(R.id.layout_subcdt_shipmentfee);
        layout_subcdt_autotext = customLayoutKeyboard.findViewById(R.id.layout_subcdt_autotext);
        layout_subcdt_pending = customLayoutKeyboard.findViewById(R.id.layout_subcdt_pending);

        lv_subcdt_pending = customLayoutKeyboard.findViewById(R.id.lv_subcdt_pending);
        adpSubcdtPendings = new AdpSubcdtPendings(responseTransactionList, this);
        lv_subcdt_pending.setAdapter(adpSubcdtPendings);
        generatorTransactions = new GeneratorTransactions(this);
/*menu autotext */

        adpAutoTexts = new AdpAutoTexts(this);

        edt_subcdt_autotext_search = customLayoutKeyboard.findViewById(R.id.edt_subcdt_autotext_search);
        btn_subcdt_autotext_search = customLayoutKeyboard.findViewById(R.id.btn_subcdt_autotext_search);
        lv_subcdt_autotext_search = customLayoutKeyboard.findViewById(R.id.lv_subcdt_autotext_search);
        btn_add_autotext = customLayoutKeyboard.findViewById(R.id.fab_autotext);

        lv_subcdt_autotext_search.setAdapter(adpAutoTexts);
        lv_subcdt_autotext_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendTextToFieldText(autotextList.get(position).getContent());
            }
        });

        btn_add_autotext.setOnClickListener(this);
        btn_subcdt_autotext_search.setOnClickListener(this);
        generatorAutoTexts = new GeneratorAutoTexts(this);
        btn_cdt_autotext.setOnClickListener(this);
/*end menu autotext */
/*menu shippment fare*/
        onPresentShippmentfare = new PShippmentFare(this, this);
        etItemWeight = customLayoutKeyboard.findViewById(R.id.etItemWeight);
        etFrom = customLayoutKeyboard.findViewById(R.id.etFrom);
        etDestination = customLayoutKeyboard.findViewById(R.id.etDestination);
        btn_count_shippmentfee = customLayoutKeyboard.findViewById(R.id.btn_count_shippmentfee);
        listOngkir = customLayoutKeyboard.findViewById(R.id.listOngkir);
        btn_shippmentfee_copytoclipboard = customLayoutKeyboard.findViewById(R.id.btn_shippmentfee_copytoclipboard);
/*end menu shippment fare*/
/*menu Make Invoice*/
        edt_add_transaction_customername = customLayoutKeyboard.findViewById(R.id.edt_add_transaction_customername);
        edt_add_transaction_customernohp = customLayoutKeyboard.findViewById(R.id.edt_add_transaction_customernohp);
        edt_add_transaction_customeraddress = customLayoutKeyboard.findViewById(R.id.edt_add_transaction_customeraddress);
        edt_add_transaction_transactionongkir = customLayoutKeyboard.findViewById(R.id.edt_add_transaction_transactionongkir);
        sp_add_transaction_bankaccount = customLayoutKeyboard.findViewById(R.id.sp_add_transaction_bankaccount);
        sp_add_transaction_chatapp = customLayoutKeyboard.findViewById(R.id.sp_add_transaction_chatapp);
        edt_add_transaction_item_qty = customLayoutKeyboard.findViewById(R.id.edt_add_transaction_item_qty);
        edt_add_transaction_item_name = customLayoutKeyboard.findViewById(R.id.edt_add_transaction_item_name);
        edt_add_transaction_item_price = customLayoutKeyboard.findViewById(R.id.edt_add_transaction_item_price);
        edt_add_transaction_transactionnote = customLayoutKeyboard.findViewById(R.id.edt_add_transaction_transactionnote);
        btn_add_transaction_done = customLayoutKeyboard.findViewById(R.id.btn_add_transaction_done);

        pAddTransaction = new PAddTransaction(this);
/*end menu Make Invoice*/


        btntoolbar_back_subcdt_content.setOnClickListener(this);
        btn_cdt_makeinvoice.setOnClickListener(this);
        btn_cdt_shippmentfee.setOnClickListener(this);
        btn_cdt_autotext.setOnClickListener(this);
        btn_cdt_pending.setOnClickListener(this);
        btn_cdt_dashboard.setOnClickListener(this);
        return customLayoutKeyboard;
    }

    private int keyboardMode = KEYBOARD_MODE_QWERTY;

    //Keyboard layout changed
    public void onChangeKeyboardMode(int mode) {
        if (mode == R.integer.qwerty) {
            if (keyboard != null) {
                keyboard = new Keyboard(this, R.xml.qwerty, KEYBOARD_MODE_NUMERIC);
                kv.setKeyboard(keyboard);
                kv.invalidateAllKeys();

                keyboardMode = KEYBOARD_MODE_NUMERIC;
            }
        } else if (mode == R.integer.numeric) {
            if (keyboard != null) {
                keyboard = new Keyboard(this, R.xml.qwerty, KEYBOARD_MODE_QWERTY);
                kv.setKeyboard(keyboard);
                kv.invalidateAllKeys();

                keyboardMode = KEYBOARD_MODE_QWERTY;
            }
        }
    }

    //Keyboard layout changer
    public void onNumberOnlyKeyboardMode() {
        keyboard = new Keyboard(this, R.xml.qwerty, KEYBOARD_MODE_NUMBER_ONLY);
        kv.setKeyboard(keyboard);
        kv.invalidateAllKeys();
    }

    public void onNormalKeyboardMode() {
        keyboard = new Keyboard(this, R.xml.qwerty, KEYBOARD_MODE_QWERTY);
        kv.setKeyboard(keyboard);
        kv.invalidateAllKeys();
    }

    //Set initial component for keyboard
    public void setKeyboardComponent() {
    }


    //Set default view for keyboard
    public void defaultViewComponent() {
        kv.setVisibility(View.VISIBLE);
        keyboard = new Keyboard(this, R.xml.qwerty, R.integer.qwerty);
        kv.setKeyboard(keyboard);


    }


    //Parsing text for auto fill soft_input text

    //Implemented soft_method default for soft_input soft_method
    //-start-
    @Override
    public void onInitializeInterface() {

    }

    private String strCounter = "";

    /**
     * @param info
     * @param restarting this soft_method called before the soft soft_input ready to receive key soft_input
     */
    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        /*
         * this variable used to count the soft_input key that have been typed so far,
         * it is called here to reset the counter when the soft_input view ready
         */
        strCounter = "";

        /*
         * this arraylist is a helper for autotext handling,
         * intended for handling quick reload after autotext added in the database
         */
    }


    @Override
    public void onWindowHidden() {

    }

    @Override
    public void onWindowShown() {
        defaultViewComponent();
    }

    private boolean caps = false;

    public void onInputCustomKeyboard(int primaryCode, EditText pInputText) {
        if (pInputText.isFocused()) {
            Editable edTextInput = pInputText.getText();
            int start = pInputText.getSelectionStart();

            switch (primaryCode) {
                case Keyboard.KEYCODE_DELETE:
                    if (edTextInput != null && start > 0)
                        edTextInput.delete(start - 1, start);
                    break;
                case Keyboard.KEYCODE_SHIFT:
                    if (!keyboard.isShifted()) {
                        caps = !caps;
                        keyboard.setShifted(caps);
                        kv.invalidateAllKeys();
                        break;
                    } else {
                        caps = false;
                        keyboard.setShifted(caps);
                        kv.invalidateAllKeys();
                        break;
                    }
                case Keyboard.KEYCODE_MODE_CHANGE:
                    onChangeKeyboardMode(keyboardMode);
                    break;
                case 144:
                    if (keyboard != null) {
                        keyboard = new Keyboard(this, R.xml.qwerty, KEYBOARD_MODE_SYMBOL);
                        kv.setKeyboard(keyboard);
                        kv.invalidateAllKeys();
                    }
                    break;
                case 145:
                    if (keyboard != null) {
                        keyboard = new Keyboard(this, R.xml.qwerty, KEYBOARD_MODE_NUMERIC);
                        kv.setKeyboard(keyboard);
                        kv.invalidateAllKeys();
                    }
                    break;
                case 146:
                    this.setBackDisposition(BACK_DISPOSITION_WILL_NOT_DISMISS);
//                    emojiPopup.showAtBottom();
                    emojiPopup.showAtLocation(kv, Gravity.NO_GRAVITY,0,0);

                    break;
                default:
                    char code = (char) primaryCode;
                    if (Character.isLetter(code) && caps) {
                        code = Character.toUpperCase(code);
                    }

                    edTextInput.insert(start, Character.toString((char) code));
            }
        }
    }

    @Override
    public void onBindInput() {

    }

    @Override
    public void onUnbindInput() {

    }

    @Override
    public void onFinishInput() {
        strCounter = "";
    }


    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        EditorInfo ei = getCurrentInputEditorInfo();

        StringBuilder strBuilder = new StringBuilder();

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
        }else if (edt_subcdt_autotext_search.isFocused()) {
            onInputCustomKeyboard(primaryCode, edt_subcdt_autotext_search);
        } else {
                switch (primaryCode) {
                    case Keyboard.KEYCODE_DELETE:
                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));

                        if (strCounter.length() > 0) {
                            strCounter = strCounter.substring(0, strCounter.length() - 1);
                        }
                        break;
                    case Keyboard.KEYCODE_SHIFT:
                        if (!keyboard.isShifted() && shiftState == 0) {
                            shiftState = 1;

                            caps = !caps;
                            keyboard.setShifted(caps);
                            kv.invalidateAllKeys();
                            break;
                        } else if (keyboard.isShifted() && shiftState == 1) {
                            shiftState = 2;
                            break;
                        } else {
                            shiftState = 0;

                            caps = false;
                            keyboard.setShifted(caps);
                            kv.invalidateAllKeys();
                            break;
                        }
                    case Keyboard.KEYCODE_DONE:
                        final EditorInfo editorInfo = getCurrentInputEditorInfo();
                        final int imeOptionsActionId = IMEUtil.getImeOptionsActionIdFromEditorInfo(editorInfo);
                        if (ic != null && IMEUtil.IME_ACTION_CUSTOM_LABEL == imeOptionsActionId) {
                            // Either we have an actionLabel and we should performEditorAction with
                            // actionId regardless of its value.
                            ic.performEditorAction(editorInfo.actionId);
                        } else if (ic != null && EditorInfo.IME_ACTION_NONE != imeOptionsActionId) {
                            // We didn't have an actionLabel, but we had another action to execute.
                            // EditorInfo.IME_ACTION_NONE explicitly means no action. In contrast,
                            // EditorInfo.IME_ACTION_UNSPECIFIED is the default value for an action, so it
                            // means there should be an action and the app didn't bother to set a specific
                            // code for it - presumably it only handles one. It does not have to be treated
                            // in any specific way: anything that is not IME_ACTION_NONE should be sent to
                            // performEditorAction.
                            ic.performEditorAction(imeOptionsActionId);
                        } else {
                            ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                            strCounter = "";
                            break;
                        }
                    case Keyboard.KEYCODE_MODE_CHANGE:
                        onChangeKeyboardMode(keyboardMode);
                        break;

                    case 144:
                        if (keyboard != null) {
                            keyboard = new Keyboard(this, R.xml.qwerty, KEYBOARD_MODE_SYMBOL);
                            kv.setKeyboard(keyboard);
                            kv.invalidateAllKeys();
                        }
                        break;
                    case 145:
                        if (keyboard != null) {
                            keyboard = new Keyboard(this, R.xml.qwerty, KEYBOARD_MODE_NUMERIC);
                            kv.setKeyboard(keyboard);
                            kv.invalidateAllKeys();
                        }
                        break;
                    case 146:
                        this.setBackDisposition(BACK_DISPOSITION_WILL_NOT_DISMISS);
//                        emojiPopup.showAtBottom();
                        emojiPopup.showAtLocation(kv, Gravity.NO_GRAVITY,0,0);
                        break;

                    default:
                        char code = (char) primaryCode;
                        if (Character.isLetter(code) && caps && shiftState == 1) {
                            code = Character.toUpperCase(code);
                            ic.commitText(String.valueOf(code), 1);

                            caps = false;
                            keyboard.setShifted(caps);
                            kv.invalidateAllKeys();

                            shiftState = 0;
                            break;
                        } else if (Character.isLetter(code) && caps && shiftState == 2) {
                            code = Character.toUpperCase(code);
                            ic.commitText(String.valueOf(code), 1);
                            break;
                        } else {
                            ic.commitText(String.valueOf(code), 1);
                            strBuilder.append(String.valueOf(code));

                            strCounter = strCounter + strBuilder;
                        }
                }
            }
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (event.getKeyCode() == 4) {
            if (emojiPopup != null) {
                if (emojiPopup.isShowing()) {
                    emojiPopup.dismiss();

                    return true;
                }
            }
        }
/*
        * reset all view
        * */
if (layout_candidatebar_main!=null)
        normalizeKeyboard();

        return super.onKeyDown(keycode, event);
    }

    @Override
    public void onPress(int primaryCode) {
//        Log.d(TAG, "onPress: top " + primaryCode);
        Keyboard currentKeyboard = kv.getKeyboard();
        List<Keyboard.Key> keys = currentKeyboard.getKeys();
        kv.invalidateKey(primaryCode);

        switch (primaryCode) {
            case Keyboard.KEYCODE_SHIFT:
                for (int i = 0; i < keys.size() - 1; i++) {
                    Keyboard.Key currentKey = keys.get(i);

                    //If your Key contains more than one code, then you will have to check if the codes array contains the primary code
                    if (currentKey.codes[0] == primaryCode) {
                        currentKey.label = null;
                        if (keyboard.isShifted() && !caps) {
                            currentKey.icon = getResources().getDrawable(R.drawable.ic_caps_lock_off_24x24);
                            break; // leave the loop once you find your match
                        } else {
                            currentKey.icon = getResources().getDrawable(R.drawable.ic_caps_lock_on_24x24);
                            break; // leave the loop once you find your match
                        }
                    }
                }
        }
    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onText(CharSequence text) {
        Log.d(TAG, "onText: " + text);
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        ic.beginBatchEdit();

        ic.commitText(text, 0);
        ic.endBatchEdit();
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
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

            case R.id.btn_subcdt_autotext_search:
                generatorAutoTexts.getAutoText(edt_subcdt_autotext_search.getText().toString());
                break;
            default:
                break;
        }

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

        if (etItemWeight.isFocused() ||
                edt_add_transaction_customernohp.isFocused() ||
                edt_add_transaction_transactionongkir.isFocused() ||
                edt_add_transaction_item_qty.isFocused() ||
                edt_add_transaction_item_price.isFocused()) {
            onNumberOnlyKeyboardMode();
        } else {
            onNormalKeyboardMode();
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
        this.arrSubdistrict = datumListAddress;
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

