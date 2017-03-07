package com.github.juniorpires.validate;


import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;


public class FormActivity extends AppCompatActivity implements Validator.ValidationListener{

    private Validator validator;

    @NotEmpty(messageResId = R.string.not_empty)
    private EditText edtNome;

    @Email(message = "Email inválido")
    private EditText edtEmail;

    private EditText edtIdade;

    @Length(min = 11)
    private EditText edtCpf;

    private Button btCadastrar;

    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.view = View.inflate(this, R.layout.activity_form,null);
        setContentView(this.view);

        this.edtNome = (EditText) this.findViewById(R.id.nome);
        this.edtEmail = (EditText) this.findViewById(R.id.email);
        this.edtIdade = (EditText) this.findViewById(R.id.idade);
        this.edtCpf = (EditText) this.findViewById(R.id.cpf);
        this.btCadastrar = (Button) this.findViewById(R.id.btCadastrar);

        this.validator = new Validator(this);
        this.validator.setValidationListener(this);

        //regra personalizada
        this.validator.put(this.edtIdade, new MaiorIdadeRule());



        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });


    }

    @Override
    public void onValidationSucceeded() {
        Snackbar.make(this.view,"Tudo OK!",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Exibe as mensagens de erro
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Snackbar.make(this.view,message,Snackbar.LENGTH_SHORT).show();
            }
        }


    }



}

