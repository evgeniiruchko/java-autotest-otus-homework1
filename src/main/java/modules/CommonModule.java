package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import prepare.User;

public class CommonModule extends AbstractModule {

    @Provides
    @Singleton
    public User getUser() {
        return new User(required("login"), required("password"));
    }

    private String required(String key) {
        String value = System.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Не задан обязательный параметр: " + key);
        }
        return value;
    }
}
