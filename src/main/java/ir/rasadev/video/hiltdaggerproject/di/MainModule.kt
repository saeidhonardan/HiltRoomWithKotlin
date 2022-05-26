package ir.rasadev.video.hiltdaggerproject.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.rasadev.video.hiltdaggerproject.R
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Named("SaeidName")
    fun provideUserName() = "Saeid Honardan"

    @Provides
    @Named("sitename")
    fun getNameFromRes(@ApplicationContext context : Context): String{
        return context.getString(R.string.app_name)
    }
}
