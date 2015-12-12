package rxjavademo;

import java.util.Arrays;
import java.util.List;
import rx.Observable;

public class PartTwo {

    public static void run() {
        // Terrible
        query("Gimme some movies!")
            .subscribe(urls -> {
                for (String url : urls) {
                    System.out.println(url);
                }
            });

        // Still terrible
        query("Gimme some movies!")
            .subscribe(urls -> {
                Observable.from(urls)
                    .subscribe(url -> System.out.println(url));
            });

        // Ye olde flatMap!
        query("Gimme some movies!")
            .flatMap(urls -> Observable.from(urls))
            .subscribe(url -> System.out.println(url));

        // Behold the power of flatMap!
        query("Gimme some movies!")
            .flatMap(urls -> Observable.from(urls))
            .flatMap(url -> getTitle(url))
            .subscribe(title -> System.out.println(title));
    }

    private static Observable<List<String>> query(String s) {
        return Observable.just(Arrays.asList("Hunger", "The Big Lebowski", "2001 A Space Odyssey", "Interstellar"));
    }

    private static Observable<String> getTitle(String url) {
        return Observable.just(String.format("http://fakeimbd.com/movies/%s", url.replace(' ', '+')));
    }
}
