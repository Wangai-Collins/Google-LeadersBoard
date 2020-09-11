package wangai.collins.leadersboard.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import wangai.collins.leadersboard.service.model.Hours;
import wangai.collins.leadersboard.service.repository.GadsRepository;

public class HoursViewModel  extends AndroidViewModel {

    private final LiveData<List<Hours>> hoursListObservable;
    public HoursViewModel(@NonNull Application application) {
        super(application);

        hoursListObservable = GadsRepository.getInstance().getHoursList();
    }

    public LiveData<List<Hours>> getHoursListObservable(){return hoursListObservable;}
}

