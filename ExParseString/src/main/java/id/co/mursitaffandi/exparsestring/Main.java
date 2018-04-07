package id.co.mursitaffandi.exparsestring;

public class Main {
    static String str_template = "selamat \n    siang bapak #customer-name  tolong bayar #cutomer_total_price ke #transaction_bank_account_name #transaction_bank_account_number #transaction_bank";

    public static void main(String[] args) {
        String[] strSplited = str_template.split(" ");

        String strResult;
        StringBuilder builder = new StringBuilder();
        for (String s : strSplited) {
            switch (s) {
                case "#customer-name":
                    s = "NAME_CUSTOMER";
                    break;
                case "#cutomer_total_price":
                    s = "ITEM_PRICE";
                    break;
                default:
                    break;
            }
            builder.append(s + " ");
        }
        strResult = builder.toString();

        System.out.println(strResult);
    }
}
