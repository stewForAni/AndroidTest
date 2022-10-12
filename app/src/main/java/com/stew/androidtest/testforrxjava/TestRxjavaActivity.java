package com.stew.androidtest.testforrxjava;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by stew on 5/14/22.
 * mail: stewforani@gmail.com
 */
public class TestRxjavaActivity extends AppCompatActivity {

    private static final String TAG = TestRxjavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        Observable<String> observable = Observable.create(emitter -> {
            Log.d(TAG, "subscribe: ");
            emitter.onNext("emitter send data --- 1");
            Log.d(TAG, "emitter1");
            emitter.onNext("emitter send data --- 2");
            Log.d(TAG, "emitter2");
            emitter.onNext("emitter send data --- 3");
            Log.d(TAG, "emitter3");
            emitter.onComplete();
        });
        observable.subscribe(observer);
//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

//        String[] names = new String[]{"qq", "ww", "ee"};
//        Disposable disposable = Observable.fromArray(names).subscribe(s ->
//                Log.d(TAG, "accept: " + s)
//        );


//        Observable.create((ObservableOnSubscribe<Drawable>) emitter -> {
//            Drawable drawable = getDrawable(R.mipmap.ic_launcher);
//            emitter.onNext(drawable);
//            emitter.onComplete();
//        }).subscribe(new Observer<Drawable>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) { }
//            @Override
//            public void onNext(@NonNull Drawable drawable) {
//                ((ImageView) (findViewById(R.id.imageView))).setImageDrawable(drawable);
//            }
//            @Override
//            public void onError(@NonNull Throwable e) { }
//            @Override
//            public void onComplete() { }
//        });

//        Disposable disposable = Observable.just(1, 2, 3, 4)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d(TAG, "accept: " + integer);
//                    }
//                });

//        Observable.create(new ObservableOnSubscribe<Drawable>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Drawable> emitter) throws Exception {
//                Log.d(TAG, "subscribe: " + Thread.currentThread());
//                Drawable drawable = TestRxjavaActivity.this.getDrawable(R.mipmap.ic_launcher);
//                emitter.onNext(drawable);
//                emitter.onComplete();
//
//            }
//        }).subscribeOn(Schedulers.io())
//          .observeOn(AndroidSchedulers.mainThread())
//          .subscribe(new Observer<Drawable>() {
//                @Override
//                public void onSubscribe(@NonNull Disposable d) {
//                    Log.d(TAG, "onSubscribe: " + Thread.currentThread());
//                }
//
//                @Override
//                public void onNext(@NonNull Drawable drawable) {
//                    Log.d(TAG, "onNext: " + Thread.currentThread());
//                    ((ImageView) (findViewById(R.id.imageView))).setImageDrawable(drawable);
//                }
//
//                @Override
//                public void onError(@NonNull Throwable e) {
//                }
//
//                @Override
//                public void onComplete() {
//                }
//            });


    }
}
