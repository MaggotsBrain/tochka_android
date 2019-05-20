package social.tochka.android.login.ui;

import android.app.Activity;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import social.tochka.android.main.MapsActivity;
import social.tochka.android.R;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final EditText confirmEditText = findViewById(R.id.confirm_password);
        final ImageButton loginButton = findViewById(R.id.login);

        final ImageButton eye_pass = findViewById(R.id.eye_pass);
        final ImageButton eye_conf = findViewById(R.id.eye_conf);


        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful

                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (passwordEditText.getText().toString().length() > 0) {
                    eye_pass.setVisibility(View.VISIBLE);
                    eye_pass.setClickable(true);
                } else {
                    eye_pass.setVisibility(View.INVISIBLE);
                    eye_pass.setClickable(false);
                }
                if (confirmEditText.getText().toString().length() > 0) {
                    eye_conf.setVisibility(View.VISIBLE);
                    eye_conf.setClickable(true);
                } else {
                    eye_conf.setVisibility(View.INVISIBLE);
                    eye_conf.setClickable(false);
                }

                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        confirmEditText.addTextChangedListener(afterTextChangedListener);

        usernameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        confirmEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        eye_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordEditText.getInputType() == 129) {
                    passwordEditText.setInputType(EditorInfo.TYPE_NULL);
                    passwordEditText.setTextSize(16);
                    eye_pass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.login_eye_color_button));
                } else {
                    passwordEditText.setInputType(129);
                    passwordEditText.setTextSize(16);
                    eye_pass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.login_eye_grey_button));
                }
            }
        });

        eye_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmEditText.getInputType() == 129) {
                    confirmEditText.setInputType(EditorInfo.TYPE_NULL);
                    confirmEditText.setTextSize(16);
                    eye_pass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.login_eye_color_button));
                } else {
                    confirmEditText.setInputType(129);
                    confirmEditText.setTextSize(16);
                    eye_pass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.login_eye_grey_button));
                }
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
