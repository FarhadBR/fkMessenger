package io.fbr.fbmassenger.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import io.fbr.fbmassenger.Model.ErrorModel;
import io.fbr.fbmassenger.Model.MessageModel;
import io.fbr.fbmassenger.Network.MassengerProvider;
import io.fbr.fbmassenger.R;
import io.fbr.fbmassenger.Utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.cacheColorHint;
import static android.R.attr.fingerprintAuthDrawable;
import static android.R.attr.onClick;
import static android.R.attr.thickness;

public class CreateOrEditMessage extends AppCompatActivity implements View.OnClickListener {

    ImageButton happyButton,confusedButton,suprizedButton,tongueButton,sendMessage;
    EditText messageBodyEdittxt;

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.confused_feel:
                setFeel(emojies.Confused);
                break;
            case R.id.happy_feel:
                setFeel(emojies.Happy);
                break;
            case R.id.suprised_feel:
                setFeel(emojies.Supprised);
                break;
            case R.id.tongue_feel:
                setFeel(emojies.tongue);
                break;
            case R.id.send_button:
                final View v=view;
                MassengerProvider provider=new MassengerProvider();

                MessageModel model=new MessageModel();
                model.body=messageBodyEdittxt.getText().toString();
                if(selectedEmoji!=emojies.noThing)
                    model.feel=getFeel();

                Call<MessageModel> call=provider.getService().createMessage(model);
                call.enqueue(new Callback<MessageModel>() {
                    @Override
                    public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                        if (response.isSuccessful()) {
                            Snackbar.make(v, "Message sent.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        } else {
                            ErrorModel errorModel=ErrorUtils.parseError(response);

                            Snackbar.make(v,"ERROR : "+errorModel.type
                                    , Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageModel> call, Throwable t) {
                        Snackbar.make(v, t.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
        }
    }


    enum emojies{Happy,Confused,Supprised,tongue,noThing};
    emojies selectedEmoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_edit_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        happyButton= (ImageButton) findViewById(R.id.happy_feel);
        confusedButton= (ImageButton) findViewById(R.id.confused_feel);
        suprizedButton= (ImageButton) findViewById(R.id.suprised_feel);
        tongueButton= (ImageButton) findViewById(R.id.tongue_feel);
        messageBodyEdittxt= (EditText) findViewById(R.id.message_body);

        happyButton.setOnClickListener(this);
        confusedButton.setOnClickListener(this);
        suprizedButton.setOnClickListener(this);
        tongueButton.setOnClickListener(this);


        setFeel(emojies.noThing);

        sendMessage= (ImageButton) findViewById(R.id.send_button);
        sendMessage.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }



    private String getFeel() {
        switch (selectedEmoji) {
            case Confused:
                return "confused";
            case Happy:
                return "happy";
            case tongue:
                return "tongue";
            case Supprised:
                return "supprised";
            default:
                return "nothind";
        }
    }

    private void setFeel(emojies emoji) {
        switch (emoji){
            case noThing:
                happyButton.setAlpha((float) 1.0);
                confusedButton.setAlpha((float) 1.0);
                suprizedButton.setAlpha((float) 1.0);
                tongueButton.setAlpha((float) 1.0);
                selectedEmoji=emojies.noThing;
                break;
            case Confused:
                happyButton.setAlpha((float)1.0);
                confusedButton.setAlpha((float) 0.5);
                suprizedButton.setAlpha((float) 1.0);
                tongueButton.setAlpha((float) 1.0);
                selectedEmoji=emojies.Confused;
                break;
            case Happy:
                happyButton.setAlpha((float) 0.5);
                confusedButton.setAlpha((float) 1.0);
                suprizedButton.setAlpha((float) 1.0);
                tongueButton.setAlpha((float) 1.0);
                selectedEmoji=emojies.Happy;
                break;
            case Supprised:
                happyButton.setAlpha((float) 1.0);
                confusedButton.setAlpha((float) 1.0);
                suprizedButton.setAlpha((float) 0.5);
                tongueButton.setAlpha((float) 1.0);
                selectedEmoji=emojies.Supprised;
                break;
            case tongue:
                happyButton.setAlpha((float) 1.0);
                confusedButton.setAlpha((float) 1.0);
                suprizedButton.setAlpha((float) 1.0);
                tongueButton.setAlpha((float) 0.5);
                selectedEmoji=emojies.tongue;
                break;

        }
    }

}
