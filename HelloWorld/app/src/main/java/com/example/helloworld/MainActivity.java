package com.example.helloworld;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.reactivestreams.Subscription;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Observable.fromArray(1,2,3,4,5).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d("from", String.valueOf(integer));
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("from", "Completed");
//            }
//        });


//        Integer[] arr = {1,2,3,4,5};
//        Observable.just(arr).subscribe(new Observer<Integer[]>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer[] integers) {
//                Log.d("just", Arrays.toString(integers));
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("just", "Completed");
//            }
//        });

//        Observable.interval(2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Long aLong) {
//                Log.d("interval", String.valueOf(aLong));
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(" interval", "Completed");
//            }
//        });

//        Observable<Long> observable = Observable.defer(() -> {
//           long time = System.currentTimeMillis();
//           return Observable.just(time);
//        });
//
//        observable.subscribe(time -> Log.d("defer", String.valueOf(time)));
//
//        try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        observable.subscribe(time -> Log.d("defer", String.valueOf(time)));

//        ObservableOnSubscribe<String> handler = emitter -> {
//          emitter.onNext("hello");
//          emitter.onNext("good by");
//          emitter.onComplete();
//        };
//
//        Observable.create(handler).subscribe(item -> Log.d("create", item), error -> error.printStackTrace() ,() -> Log.d("create", "completed"));

//        Observable.just(1,2,3)
//                .map(x -> x * x)
//                .subscribe(item -> Log.d("map", String.valueOf(item)));

//        Observable.just(1,2,3,4,5,6)
//                .filter(x -> x%2 == 0)
//                .subscribe(item -> Log.d("filter", String.valueOf(item)));

//        Observable.just(1,2,3)
//                .mergeWith(Observable.just(4,5,6))
//                .subscribe(item -> Log.d("merge", String.valueOf(item)));

//        Observable<String> firstNames = Observable.just("James", "Jean-Luc", "Benjamin");
//        Observable<String> lastNames = Observable.just("Kirk", "Picard", "Sisko");
//        firstNames.zipWith(lastNames, (first, last) -> first + " " + last)
//                .subscribe(item -> Log.d("zip", item));

//        Disposable disposable = Observable.just(1,2,3).subscribe();
//        disposable.dispose();

        // Creating Observables


        // Filtering Observables


        // Transforming Observables
        Observable.just(1,2,3,4,5,6)
                .map(x -> x*2)
                .subscribe(item -> Log.d("map", String.valueOf(item)), err -> {}, () -> {});

        Observable.just(1,2,3)
                .flatMap(x -> {
                    return Observable.just(x +"a", x + "b" , x + "c");
                }).subscribe(item -> Log.d("flatmap", item), errr -> {}, () -> {});


        // Combining Observables
        Observable.just(1,2,3)
                .mergeWith(Observable.just(4,5,6))
                .subscribe(item -> Log.d("merge", String.valueOf(item)), err -> {}, () -> {});

        Observable.just("Nguyen", "Tran", "Le")
                .zipWith(Observable.just("Duc", "Hai", "Dung"), (a, b) -> a + " " + b)
                .subscribe(item -> Log.d("zip", item), err -> {}, () -> {});

        // Observable Utility Operators

        Integer[][] array = new Integer[][]{{1,2,3,4,5,6},{11,22,33,44,55},{111,222,333,444,555}};
        Observable<Integer[]> observable = Observable.fromArray(array);
        Observable delay=observable.delay(10,TimeUnit.SECONDS);
        delay.flatMap(new Function<Integer[], ObservableSource<?>>() {

            @Override
            public ObservableSource<?> apply(Integer[] integers) throws Exception {
                Log.d("tag","FlatMap"+Thread.currentThread().getName());
                return Observable.fromArray(integers);
            }
        }).filter(new Predicate() {
            @Override
            public boolean test(Object o) throws Exception {
                return Integer.parseInt(o.toString())%2==1;
            }
        }).observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d("tag","Subscribe"+o.toString()+" "+Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
