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


        // ---------------- Creating Observables

//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                try {
//                    for (int i = 1; i < 5; i++) {
//                        emitter.onNext(i);
//                    }
//                    emitter.onComplete();
//                }
//                catch (Exception e)
//                {
//                    emitter.onError(e);
//                }
//            }
//
//
//        } ).subscribe(new Observer<Integer>() {
//
//
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer item) {
//                Log.d("onNext", String.valueOf(item));
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("onError", e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("onComplete",  "Sequence complete.");
//            }
//        });

        //***********Just*******************//

        //1
//        String greeting = "Hello world!";
//
//        Observable<String> observable = Observable.just(greeting);
//
//        observable.subscribe(item -> Log.d("onNext",item));
//
//
        //2
//        Observable<Object> observable1 = Observable.just("1", "A", "3.2", "def");
//
//        observable1.subscribe(item -> Log.d("onNext", String.valueOf(item)), error -> error.printStackTrace(),
//                () -> System.out.println());

        //************from**************//

//        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
//
//        Observable<Integer> observable = Observable.fromIterable(list);
//
//        observable.subscribe(item -> Log.d("onNext", String.valueOf(item)), error -> error.printStackTrace(),
//                () -> System.out.println("Done"));

        //**********defer***********//

//        Observable<Long> observable = Observable.defer(() -> {
//            long time = System.currentTimeMillis();
//            return Observable.just(time);
//        });
//
//        observable.subscribe(time -> Log.d("onNext", String.valueOf(time)));
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        observable.subscribe(time -> Log.d("onNext", String.valueOf(time)));

        //***********interval***************//

//        Observable<Long> clock = Observable.interval(1, TimeUnit.SECONDS);
//
//        clock.subscribe(time -> {
//            if (time % 2 == 0) {
//                Log.d("onNext", "Tick");
//            } else {
//                Log.d("onNext", "Tock");
//            }
//        });



        // ----------------- Filtering Observables
//        Observable<Integer> observable = Observable.just(1,2,3,4,5,6)
//                .filter(x -> x % 2 == 0);
//        observable.subscribe(x-> Log.d("onNext", String.valueOf(x)));

        //****************take*****************//

//        Observable<Integer> observable = Observable.just(1,2,3,4,5,6)
//                .take(3);
//        observable.subscribe(x-> Log.d("onNext", String.valueOf(x)));

        //************distinct*******************//
//
//        Observable.just(2, 3, 4, 4, 2, 1)
//                .distinct()
//                .subscribe(x-> Log.d("onNext", String.valueOf(x)));

        //************skip**************//

//        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//
//        source.skip(4)
//                .subscribe(x-> Log.d("onNext", String.valueOf(x)));



        // ----------------- Transforming Observables
//        Observable.just(1,2,3,4,5,6)
//                .map(x -> x*2)
//                .subscribe(item -> Log.d("map", String.valueOf(item)), err -> {}, () -> {});
//
//        Observable.just(1,2,3)
//                .flatMap(x -> {
//                    return Observable.just(x +"a", x + "b" , x + "c");
//                }).subscribe(item -> Log.d("flatmap", item), errr -> {}, () -> {});


        // ----------------- Combining Observables
//        Observable.just(1,2,3)
//                .mergeWith(Observable.just(4,5,6))
//                .subscribe(item -> Log.d("merge", String.valueOf(item)), err -> {}, () -> {});
//
//        Observable.just("Nguyen", "Tran", "Le")
//                .zipWith(Observable.just("Duc", "Hai", "Dung"), (a, b) -> a + " " + b)
//                .subscribe(item -> Log.d("zip", item), err -> {}, () -> {});

        // ------------------ Observable Utility Operators

//        Integer[][] array = new Integer[][]{{1,2,3,4,5,6},{11,22,33,44,55},{111,222,333,444,555}};
//        Observable<Integer[]> observable = Observable.fromArray(array);
//        Observable delay=observable.delay(10,TimeUnit.SECONDS);
//        delay.flatMap(new Function<Integer[], ObservableSource<?>>() {
//
//            @Override
//            public ObservableSource<?> apply(Integer[] integers) throws Exception {
//                Log.d("tag","FlatMap"+Thread.currentThread().getName());
//                return Observable.fromArray(integers);
//            }
//        }).filter(new Predicate() {
//            @Override
//            public boolean test(Object o) throws Exception {
//                return Integer.parseInt(o.toString())%2==1;
//            }
//        }).observeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(new Observer<Object>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//                        Log.d("tag","Subscribe"+o.toString()+" "+Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
