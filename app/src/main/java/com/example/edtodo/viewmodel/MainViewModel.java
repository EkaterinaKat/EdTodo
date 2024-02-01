package com.example.edtodo.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.edtodo.db.NoteDatabase;
import com.example.edtodo.logic.Note;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final NoteDatabase database;
    private final MutableLiveData<Integer> countLD = new MutableLiveData<>();
    private int count = 0;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = NoteDatabase.getInstance(application);
    }

    public void remove(Note note) {
        Disposable disposable = database.noteDao().remove(note.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.i("MainViewModel", "note removed"));
        compositeDisposable.add(disposable);
    }

    public LiveData<List<Note>> getNotes() {
        return database.noteDao().getNotes();
    }

    public void incrementCount() {
        count++;
        countLD.setValue(count);
    }

    public MutableLiveData<Integer> getCountLD() {
        return countLD;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
