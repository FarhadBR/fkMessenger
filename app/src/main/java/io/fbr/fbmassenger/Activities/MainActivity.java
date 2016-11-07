package io.fbr.fbmassenger.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import io.fbr.fbmassenger.Adapters.MessageAdapter;
import io.fbr.fbmassenger.Model.ErrorModel;
import io.fbr.fbmassenger.Model.MessageModel;
import io.fbr.fbmassenger.Network.MassengerProvider;
import io.fbr.fbmassenger.Network.MassengerService;
import io.fbr.fbmassenger.R;
import io.fbr.fbmassenger.Utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView messagesRy;
    private MessageAdapter adapter;
    private MassengerProvider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent=new Intent(MainActivity.this,CreateOrEditMessage.class);
                startActivity(intent);
            }
        });

        messagesRy= (RecyclerView) findViewById(R.id.ry_messages);
        messagesRy.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MessageAdapter(this);
        messagesRy.setAdapter(adapter);

        provider=new MassengerProvider();

        getDataFromServer();
    }

    private void getDataFromServer() {
        Call<List<MessageModel>> call=provider.getService().getMessages();
        call.enqueue(new Callback<List<MessageModel>>() {
            @Override
            public void onResponse(Call<List<MessageModel>> call, Response<List<MessageModel>> response) {
                if (response.isSuccessful()){
                    adapter.updateAdapterDate(response.body());
                    adapter.notifyDataSetChanged();
                }
                else {
                    ErrorModel errorModel= ErrorUtils.parseError(response);
                    Snackbar.make(null,errorModel.type,Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            @Override
            public void onFailure(Call<List<MessageModel>> call, Throwable t) {
                Snackbar.make(null,t.getCause().getMessage(),Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.newMessage) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
