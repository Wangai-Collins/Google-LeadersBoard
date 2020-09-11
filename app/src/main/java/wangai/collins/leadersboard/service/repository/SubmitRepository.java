package wangai.collins.leadersboard.service.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitRepository {
    private static final String TAG = "SubmitRepository";

    private GadsService gadsService;
    private static SubmitRepository submitRepository;

    public SubmitRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GadsService.SUBMIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gadsService = retrofit.create(GadsService.class);
    }
    public synchronized static SubmitRepository getInstance(){

        //TODO No need to implement this singleton later since Dagger will handle it ...

        if (submitRepository==null){
            submitRepository = new SubmitRepository();
        }

        return submitRepository;
    }

    public LiveData<Integer> submitProject(String email, String first_name, String last_name, String project_link){

        final MutableLiveData<Integer> projectSubmission = new MutableLiveData<>();


        Log.d("value", "submitProject: values passed " + email + first_name+ last_name+project_link);
        gadsService.submitProject(email, first_name, last_name, project_link).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    projectSubmission.setValue(response.code());
                    Log.d("values", "onResponse: " + response.code());
                }
                projectSubmission.setValue(response.code());
                Log.d("values", "onResponse: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
        return  projectSubmission;
    }
}
