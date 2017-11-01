package ru.sushistudio.guestbook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by android on 01.11.17.
 */

public class DialogUnlock extends DialogFragment {

    @BindView(R.id.dialog_password_et)
    EditText mPasswordEditText;
    @BindView(R.id.dialog_cancel_btn)
    Button mCancelButton;
    @BindView(R.id.dialog_ok_btn)
    Button mOkButton;

    private DialogUnlockListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_unlock, container, false);
        ButterKnife.bind(this, view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPasswordEditText.setError(null);
                if (mPasswordEditText.getText().toString().equals("85321")) {
                    if (mListener != null) {
                        mListener.onPasswordSuccess();
                        dismiss();
                    } else {
                        DialogUnlock.this.dismiss();
                    }
                } else {
                    mPasswordEditText.setError("Неверный пароль");
                }
            }
        });
    }

    public interface DialogUnlockListener {
        void onPasswordSuccess();
    }
    public void setListener(DialogUnlockListener listener) {
        mListener = listener;
    }
}
