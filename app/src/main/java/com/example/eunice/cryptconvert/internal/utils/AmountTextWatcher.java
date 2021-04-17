package com.example.eunice.cryptconvert.internal.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

public class AmountTextWatcher implements TextWatcher {
        private DecimalFormat df;
        private EditText editText;
        private static String thousandSeparator;
        private static String decimalMarker;
        private int cursorPosition;

        public AmountTextWatcher (EditText editText) {
            this.editText = editText;
            df = new DecimalFormat("#,###.##");
            df.setDecimalSeparatorAlwaysShown(true);
            thousandSeparator = Character.toString(df.getDecimalFormatSymbols().getGroupingSeparator());
            decimalMarker = Character.toString(df.getDecimalFormatSymbols().getDecimalSeparator());
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            cursorPosition = editText.getText().toString().length() - editText.getSelectionStart();
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(Editable s) {
            try {
                editText.removeTextChangedListener(this);
                String value = editText.getText().toString();

                if (value != null && !value.equals("")) {
                    if (value.startsWith(decimalMarker)) {
                        String text = "0" + decimalMarker;
                        editText.setText(text);
                    }
                    if (value.startsWith("0") && !value.startsWith("0" + decimalMarker)) {
                        int index = 0;
                        while (index < value.length() && value.charAt(index) == '0') {
                            index++;
                        }
                        String newValue = Character.toString(value.charAt(0));
                        if (index != 0) {
                            newValue = value.charAt(0) + value.substring(index);
                        }
                        editText.setText(newValue);
                    }
                    String str = editText.getText().toString().replaceAll(thousandSeparator, "");
                    if (!value.equals("")) {
                        editText.setText(getDecimalFormattedString(str));
                    }
                    editText.setSelection(editText.getText().toString().length());
                }

                //setting the cursor back to where it was
                editText.setSelection(editText.getText().toString().length() - cursorPosition);
                editText.addTextChangedListener(this);
            } catch (Exception ex) {
                ex.printStackTrace();
                editText.addTextChangedListener(this);
            }
        }

        private static String getDecimalFormattedString(String value) {

            String[] splitValue = value.split("\\.");
            String beforeDecimal = value;
            String afterDecimal = null;
            String finalResult = "";

            if (splitValue.length == 2) {
                beforeDecimal = splitValue[0];
                afterDecimal = splitValue[1];
            }

            int count = 0;
            for (int i = beforeDecimal.length() - 1; i >= 0 ; i--) {
                finalResult = beforeDecimal.charAt(i) + finalResult;
                count++;
                if (count == 3 && i > 0) {
                    finalResult = thousandSeparator + finalResult;
                    count = 0;
                }
            }

            if (afterDecimal != null) {
                finalResult = finalResult + decimalMarker + afterDecimal;
            }

            return finalResult;
        }

        /*
         * Returns the string after removing all the thousands separators.
         * */
        public static String trimCommas(String string) {
            return string.replace(thousandSeparator,"");
        }
    }
