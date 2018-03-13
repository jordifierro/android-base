package com.jordifierro.androidbase.presentation.dependency.component;

import com.jordifierro.androidbase.presentation.dependency.FragmentScope;
import com.jordifierro.androidbase.presentation.dependency.module.LoginModule;
import com.jordifierro.androidbase.presentation.dependency.module.NoteCreateModule;
import com.jordifierro.androidbase.presentation.dependency.module.NoteDetailModule;
import com.jordifierro.androidbase.presentation.dependency.module.NoteEditModule;
import com.jordifierro.androidbase.presentation.dependency.module.NotesModule;
import com.jordifierro.androidbase.presentation.dependency.module.RegisterModule;
import com.jordifierro.androidbase.presentation.dependency.module.ResetPasswordModule;
import com.jordifierro.androidbase.presentation.dependency.module.SettingsModule;
import com.jordifierro.androidbase.presentation.view.fragment.LoginFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NoteCreateFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NoteDetailFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NoteEditFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NotesFragment;
import com.jordifierro.androidbase.presentation.view.fragment.RegisterFragment;
import com.jordifierro.androidbase.presentation.view.fragment.ResetPasswordFragment;
import com.jordifierro.androidbase.presentation.view.fragment.SettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DemoFragmentInjector {

    @FragmentScope
    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginFragment bindLoginFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {RegisterModule.class})
    abstract RegisterFragment bindRegisterFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {NotesModule.class})
    abstract NotesFragment bindNotesFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {NoteDetailModule.class})
    abstract NoteDetailFragment bindNoteDetailFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {NoteCreateModule.class})
    abstract NoteCreateFragment bindNoteCreateFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {NoteEditModule.class})
    abstract NoteEditFragment bindNoteEditFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {SettingsModule.class})
    abstract SettingsFragment bindSettingsFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {ResetPasswordModule.class})
    abstract ResetPasswordFragment bindResetPasswordFragment();

}
