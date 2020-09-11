package wangai.collins.leadersboard.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import wangai.collins.leadersboard.service.model.Project;
import wangai.collins.leadersboard.service.repository.SubmitRepository;

public class SubmitProjectViewModel extends AndroidViewModel {

    public LiveData<Integer> submitProjectObservable;



    public SubmitProjectViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<Integer> setSubmitProjectObservable(Project project){
        Log.d("values", "SubmitProjectViewModel: " +project.getEmail());

        submitProjectObservable = SubmitRepository.getInstance().submitProject(project.getEmail(), project.getFirst_name(), project.getLast_name(),project.getProject_link());
        return submitProjectObservable;}
}

