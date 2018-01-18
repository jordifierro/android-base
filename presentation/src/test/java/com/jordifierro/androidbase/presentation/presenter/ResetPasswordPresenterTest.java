package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.data.net.error.RestApiErrorException;
import com.jordifierro.androidbase.domain.entity.MessageEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.interactor.user.ResetPasswordUseCase;
import com.jordifierro.androidbase.presentation.view.ResetPasswordView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class ResetPasswordPresenterTest {

    @Mock
    ResetPasswordUseCase mockResetPasswordUseCase;
    @Mock
    ResetPasswordView mockResetPasswordView;
    @Mock
    Observable mockObservable;

    private ResetPasswordPresenter resetPasswordPresenter;
    private ResetPasswordPresenter.ResetPasswordSubscriber resetPasswordSubscriber;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.resetPasswordPresenter = new ResetPasswordPresenter(mockResetPasswordView, this.mockResetPasswordUseCase);
        this.resetPasswordSubscriber = this.resetPasswordPresenter.new ResetPasswordSubscriber();
    }

    @Test
    public void testDestroy() {

        this.resetPasswordPresenter.destroy();

        verify(this.mockResetPasswordUseCase).unsubscribe();
        assertNull(this.resetPasswordPresenter.view());
    }

    @Test
    public void testRegisterUser() throws Exception {

        this.resetPasswordPresenter.resetPassword("email", "password", "password");

        verify(this.mockResetPasswordView).showLoader();
        verify(this.mockResetPasswordUseCase).setParams(any(UserEntity.class));
        verify(this.mockResetPasswordUseCase).execute(any(BasePresenter.BaseSubscriber.class));
    }

    @Test
    public void testSubscriberOnCompleted() {

        this.resetPasswordSubscriber.onComplete();

        verify(this.mockResetPasswordView).hideLoader();
    }

    @Test
    public void testSubscriberOnError() {

        this.resetPasswordSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockResetPasswordView).hideLoader();
        verify(this.mockResetPasswordView).handleError(any(Throwable.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSubscriberOnNext() {

        this.resetPasswordSubscriber.onNext(new MessageEntity("message"));

        verify(this.mockResetPasswordView).hideLoader();
        verify(this.mockResetPasswordView).showMessage("message");
        verify(this.mockResetPasswordView).close();
    }

}
