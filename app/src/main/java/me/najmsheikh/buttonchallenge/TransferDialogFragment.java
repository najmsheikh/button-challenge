package me.najmsheikh.buttonchallenge;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import me.najmsheikh.buttonchallenge.models.Transfer;


/**
 * A dialog Fragment that aids in initiating a {@link Transfer}
 */
public class TransferDialogFragment extends DialogFragment {

    private View mRootView;
    private Button mTransferButton;
    private Button mCancelButton;
    private EditText mIdEditText;
    private EditText mAmountEditText;

    public TransferDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize Views
        mRootView = inflater.inflate(R.layout.fragment_transfer_dialog, container, false);
        mTransferButton = mRootView.findViewById(R.id.button_transfer);
        mCancelButton = mRootView.findViewById(R.id.button_cancel);
        mIdEditText = mRootView.findViewById(R.id.et_transfer_id);
        mAmountEditText = mRootView.findViewById(R.id.et_transfer_amount);

        // Handle clicking the "Transfer" button in the dialog
        mTransferButton.setOnClickListener(v -> {
            Long id = Long.valueOf(mIdEditText.getText().toString());
            String amount = mAmountEditText.getText().toString();

            ((UserActivity) getActivity()).addTransfer(new Transfer(amount, id));
            mIdEditText.getText().clear();
            mAmountEditText.getText().clear();
            dismiss();
        });

        // Handle clicking the "Cancel" button in the dialog
        mCancelButton.setOnClickListener(v -> dismiss());

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Ensure proper dialog dimensions
        getDialog().getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}
