package com.example.edtodo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.edtodo.db.NoteDatabase;
import com.example.edtodo.logic.Note;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CreateNoteViewModel extends AndroidViewModel {
    private final NoteDatabase database;
    private final MutableLiveData<Boolean> shouldCloseScreen = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CreateNoteViewModel(@NonNull Application application) {
        super(application);
        database = NoteDatabase.getInstance(application);
    }

    public void saveNote(Note note) {
        Disposable disposable = database.noteDao().add(note)
                //данная строчка командует чтобы add(note) выполнялся в фоновом потоке
                .subscribeOn(Schedulers.io())
                //данная строчка командует чтобы все инструкции указанные ниже выполнялись в главном потоке
                .observeOn(AndroidSchedulers.mainThread())
                //если у Completable не вызвать subscribe тогда метод add(note) не будет выполнен
                .subscribe(() -> {
                    shouldCloseScreen.setValue(true);
                });
        compositeDisposable.add(disposable);
    }

    public MutableLiveData<Boolean> getShouldCloseScreen() {
        return shouldCloseScreen;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
