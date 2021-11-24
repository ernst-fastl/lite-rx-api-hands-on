package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {

    //========================================================================================

    // TODO Capitalize the user username, firstname and lastname
    Mono<User> capitalizeOne(Mono<User> mono) {
        Function<Mono<User>, Mono<User>> toUpper = userMono -> userMono.map(Part04Transform::toUpperCase);
        return mono.transform(toUpper);
    }

    private static User toUpperCase(User user) {
        return new User(user.getUsername().toUpperCase(),
                        user.getFirstname().toUpperCase(),
                        user.getLastname().toUpperCase());
    }

    //========================================================================================

    // TODO Capitalize the users username, firstName and lastName
    Flux<User> capitalizeMany(Flux<User> flux) {
        Function<Flux<User>, Flux<User>> toUpper = uFlux -> uFlux.map(Part04Transform::toUpperCase);
        return flux.transform(toUpper);
    }

    //========================================================================================

    // TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.flatMap(this::asyncCapitalizeUser);
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(),
                                  u.getFirstname().toUpperCase(),
                                  u.getLastname().toUpperCase()));
    }
}
