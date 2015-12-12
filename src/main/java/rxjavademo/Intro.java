package rxjavademo;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class Intro {

    public static void run() {
        // *****
        // Let's do this the verbose way
        // *****

        // Create the Observable, emit a nice "Hello RxJava" once
        Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    public void call (Subscriber<? super String> sub) {
                        sub.onNext("Hello RxJava!");
                        sub.onCompleted();
                    }
                }
        );

        // Define the Subscriber, just print out the String that was emitted from the Observer
        Subscriber<String> subscriber = new Subscriber<String>() {
            public void onCompleted() {

            }

            public void onError(Throwable throwable) {

            }

            public void onNext(String s) {
                System.out.println(s);
            }
        };

        // Let's hook up these bad boys
        observable.subscribe(subscriber);

        // *****
        // Let's do that a little simpler
        // *****

        // Create the Observable with Observable.just() so that it only emits once
        Observable<String> simpleObservable = Observable.just("Hello RxJava the easy way!");

        // Let's create an Action1, an action to be performed with a SINGLE parameter
        Action1<String> onNextAction = new Action1<String>() {
            public void call(String s) {
                System.out.println(s);
            }
        };

        // Observable.subscribe() can take one to three Actions, each replacing onNextAction, onCompleteAction or onErrorAction
        // Like this: simpleObservable.subscribe(onNextAction, onCompleteAction, onErrorAction
        // We're only interested in onNextAction right now, so we'll do it like this
        simpleObservable.subscribe(onNextAction);

        // *****
        // Let's make it even simpler, let's chain 'em together
        // *****

        Observable.just("Hello from the RxJava chain!")
                .subscribe(new Action1<String>() {
                    public void call(String s) {
                        System.out.println(s);
                    }
                });

        // *****
        // Let's make it even simpler-er, using Java 8 lambdas
        // *****

        Observable.just("Hello from the lambdafied RxJava!")
                .subscribe(s -> System.out.println(s));

        // *****
        // Let's introduce some operators
        // *****

        Observable.just("Hello")
                .map(s -> String.format("%s good man!", s))
                .subscribe(s -> System.out.println(s));
    }
}
