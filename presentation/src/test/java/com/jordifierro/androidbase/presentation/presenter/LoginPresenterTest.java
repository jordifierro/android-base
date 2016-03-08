package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.data.net.error.RestApiErrorException;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.interactor.user.DoLoginUseCase;
import com.jordifierro.androidbase.presentation.view.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class LoginPresenterTest {

    @Mock DoLoginUseCase mockDoLoginUseCase;
    @Mock LoginView mockLoginView;
    @Mock Observable mockObservable;

    private LoginPresenter loginPresenter;
    private BasePresenter.BaseSubscriber baseSubscriber;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.loginPresenter = new LoginPresenter(this.mockDoLoginUseCase);
        this.loginPresenter.initWithView(this.mockLoginView);
        this.baseSubscriber = this.loginPresenter.new BaseSubscriber();
    }

    @Test
    public void testDestroy() {

        this.loginPresenter.destroy();

        verify(mockDoLoginUseCase).unsubscribe();
    }

    @Test
    public void testLoginUser() throws Exception {

        this.loginPresenter.loginUser("email", "password");

        verify(this.mockLoginView).showLoader();
        verify(this.mockDoLoginUseCase).setParams(any(UserEntity.class));
        verify(this.mockDoLoginUseCase).execute(any(BasePresenter.BaseSubscriber.class));
    }

    @Test
    public void testSubscriberOnCompleted() {

        this.baseSubscriber.onCompleted();

        verify(this.mockLoginView).hideLoader();
    }

    @Test
    public void testSubscriberOnError() {

        this.baseSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockLoginView).hideLoader();
        verify(this.mockLoginView).showError(any(String.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSubscriberOnNext() {

        this.baseSubscriber.onNext(new UserEntity("email"));

        verify(this.mockLoginView).showMessage(any(String.class));
    }

}
