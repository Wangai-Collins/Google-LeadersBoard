package wangai.collins.leadersboard.service.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wangai.collins.leadersboard.service.model.Hours;
import wangai.collins.leadersboard.service.model.SkillIqs;

public class GadsRepository {
    private GadsService gadsService;
    private static GadsRepository gadsRepository;

    private GadsRepository (){
        //TODO this gadsService instance will be injected using Dagger later ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GadsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gadsService =retrofit.create(GadsService.class);
    }

    public synchronized static GadsRepository getInstance(){
        //TODO No need to implement this singleton later since Dagger will handle it ...

        if (gadsRepository==null){
            gadsRepository = new GadsRepository();
        }

        return gadsRepository;
    }

    // this method returns a live data that will be used in the GADS Hours view model
    public LiveData<List<Hours>> getHoursList(){

        final MutableLiveData<List<Hours>> data = new MutableLiveData<>();

        gadsService.getAllHours().enqueue(new Callback<List<Hours>>() {
            @Override
            public void onResponse(Call<List<Hours>> call, Response<List<Hours>> response) {
                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Hours>> call, Throwable t) {
                // TODO better error handling later ...
//                data.setValue(null);
            }
        });

        return data;
    }

    // this method returns a liveData that will be used in the GADSIq view model
    public LiveData<List<SkillIqs>> getSkillIqList(){
        final MutableLiveData<List<SkillIqs>> data = new MutableLiveData<>();

        gadsService.getAllSkillIqs().enqueue(new Callback<List<SkillIqs>>() {
            @Override
            public void onResponse(Call<List<SkillIqs>> call, Response<List<SkillIqs>> response) {
                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillIqs>> call, Throwable t) {
                // TODO better error handling later ...
                // data.setValue(0);
            }
        });

        return data;
    }

    private void simulateDelay() {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
