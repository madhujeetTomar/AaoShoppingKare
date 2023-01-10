package com.example.aaoshoppingkare.di

import com.example.aaoshoppingkare.SignInFragment
import com.example.aaoshoppingkare.SignUpFragment
import com.example.aaoshoppingkare.view.user.LoginActivity
import dagger.Subcomponent
import javax.inject.Scope

@ActivityScope
@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    // All LoginActivity, LoginUsernameFragment and LoginPasswordFragment
    // request injection from LoginComponent. The graph needs to satisfy
    // all the dependencies of the fields those classes are injecting
    fun inject(loginActivity: LoginActivity)
    fun inject(usernameFragment: SignInFragment)
    fun inject(passwordFragment: SignUpFragment)
}

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope