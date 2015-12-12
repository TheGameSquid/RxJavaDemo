package rxjavademo;

import rx.Observable;
import rx.Subscriber;

public class App
{
    public static void main( String[] args )
    {
        Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    public void call (Subscriber<? super String> sub) {
                        sub.onNext("Hello RxJava!");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> subscriber = new Subscriber<String>() {
            public void onCompleted() {

            }

            public void onError(Throwable throwable) {

            }

            public void onNext(String s) {
                System.out.println(s);
            }
        };

        observable.subscribe(subscriber);
    }
}
