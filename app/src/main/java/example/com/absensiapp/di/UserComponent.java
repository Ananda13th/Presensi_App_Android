package example.com.absensiapp.di;

import dagger.Component;
import example.com.absensiapp.viewmodel.UserViewModel;

@Component(modules = UserModule.class)
public interface UserComponent {
    void inject(UserViewModel userViewModel);
}