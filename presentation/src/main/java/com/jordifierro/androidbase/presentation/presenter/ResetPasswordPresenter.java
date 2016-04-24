package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.MessageEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.interactor.user.ResetPasswordUseCase;
import com.jordifierro.androidbase.presentation.dependency.ActivityScope;
import com.jordifierro.androidbase.presentation.view.BaseView;
import com.jordifierro.androidbase.presentation.view.ResetPasswordView;

import javax.inject.Inject;

@ActivityScope
public class ResetPasswordPresenter extends BasePresenter implements Presenter {

    private ResetPasswordUseCase resetPasswordUseCase;
    ResetPasswordView resetPasswordView;

    @Inject
    public ResetPasswordPresenter(ResetPasswordUseCase resetPasswordUseCase) {
        super(resetPasswordUseCase);
        this.resetPasswordUseCase = resetPasswordUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.resetPasswordView = (ResetPasswordView) view;
    }

    @Override
    public void destroy() {
        super.destroy();
        this.resetPasswordView = null;
    }

    public void resetPassword(String email, String newPassword, String newPasswordConfirmation) {
        UserEntity user = new UserEntity(email);
        user.setNewPassword(newPassword);
        user.setNewPasswordConfirmation(newPasswordConfirmation);

        this.showLoader();
        this.resetPasswordUseCase.setParams(user);
        this.resetPasswordUseCase.execute(new ResetPasswordSubscriber());
    }

    protected class ResetPasswordSubscriber extends BaseSubscriber<MessageEntity> {

        @Override
        public void onNext(MessageEntity message) {
            ResetPasswordPresenter.this.hideLoader();
            ResetPasswordPresenter.this.resetPasswordView.showMessage(message.getMessage());
            ResetPasswordPresenter.this.resetPasswordView.close();
        }

    }

}
