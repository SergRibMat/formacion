package com.atsistemas.myapplication.splash_activity.ui

import android.os.Bundle
import com.atsistemas.myapplication.commons.BaseActivity
import com.atsistemas.myapplication.commons.Constants
import com.atsistemas.myapplication.commons.removeFirstLastChar
import com.atsistemas.myapplication.databinding.ActivitySplashBinding
import com.atsistemas.myapplication.home_activity.HomeActivity
import org.jetbrains.anko.startActivity

/**
 * Created by Juan Manuel Rincón on 3/8/21.
 */
class SplashActivity: BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        object: Thread(){
            override fun run(){
                try{
                    sleep(Constants.LOADING_SLEEP)
                }catch (ie: InterruptedException){
                    ie.printStackTrace()
                }finally {
                    startActivity<HomeActivity>()
                    finish()
                }
            }
        }.start()

        val hol =  "hola".removeFirstLastChar()
    }


}