package wangai.collins.leadersboard.view.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import wangai.collins.leadersboard.R;
import wangai.collins.leadersboard.service.model.Project;
import wangai.collins.leadersboard.viewmodel.SubmitProjectViewModel;
import wangai.collins.leadersboard.databinding.ActivitySubmitBinding;

public class SubmitActivity extends AppCompatActivity {

    private static final String TAG = "SubmitActivity";
    ActivitySubmitBinding binding;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submit);

        ToolBarSetup();

    }

    public void ToolBarSetup(){

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void toSubmitAction(View view) {
        showPromptDialogue();
    }

    public void handleSubmission(){
        String email  = binding.emailAddress.getText().toString().trim();
        String first_name  = binding.firstName.getText().toString().trim();
        String last_name  = binding.lastName.getText().toString().trim();
        String projectLink  = binding.emailAddress.getText().toString().trim();

        Project project = new Project(email,first_name,last_name,projectLink);

        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected){
            showFailedDialogue();
        }else {


            final SubmitProjectViewModel submitProjectViewModel = ViewModelProviders.of(this).get(SubmitProjectViewModel.class);
            submitProjectViewModel.setSubmitProjectObservable(project).observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    if (integer>=199 && integer<300){
                        //Toast.makeText(SubmitActivity.this, "Data sent successfully",Toast.LENGTH_LONG).show();
                        showSuccessDialogue();
                    }
                    else {
                        showFailedDialogue();
                    }

                }
            });
        }
    }

    public void backButtonPressed(View view) {
        onBackPressed();
        finish();
    }

    public void showSuccessDialogue(){

        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.success_dialogue, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showFailedDialogue(){
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.failed_dialogue, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void showPromptDialogue(){
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.prompt_dialogue, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button button = dialogView.findViewById(R.id.buttonOk);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmission(); // handling submission
                alertDialog.dismiss();
            }
        });

    }
}