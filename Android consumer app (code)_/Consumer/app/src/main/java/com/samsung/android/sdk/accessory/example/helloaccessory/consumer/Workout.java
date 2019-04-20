package com.samsung.android.sdk.accessory.example.helloaccessory.consumer;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;


enum Level {
    FIRST,
    SECOND,
    THIRD,
    FOURTH,
    FIFTH,
    END
}

public class Workout extends AppCompatActivity {

    MediaPlayer slow1;
    MediaPlayer slow2;
    MediaPlayer slow3;
    MediaPlayer slow4;
    MediaPlayer slow5;
    MediaPlayer slow6;
    MediaPlayer[] slow;

    MediaPlayer fast1;
    MediaPlayer fast2;
    MediaPlayer fast3;
    MediaPlayer fast4;
    MediaPlayer fast5;
    MediaPlayer fast6;
    MediaPlayer[] fast;

    MediaPlayer tooSlow;
    MediaPlayer tooFast;
    MediaPlayer warmup;
    MediaPlayer sprint;
    MediaPlayer sprint2;
    MediaPlayer cooldown;
    MediaPlayer cooldown2;
    MediaPlayer end;

    MediaPlayer holder;

    Context context;

    Boolean first = true;
    Boolean started = false;
    Boolean warned = false;

    static Timer timer = new Timer();
    Level currentLevel;




    public Workout(Context context) {
        this.context = context;
        currentLevel = Level.FIRST;

    }

    public void setMusic() {
        try {
            tooFast = MediaPlayer.create(context, R.raw.slow_down);
            tooSlow = MediaPlayer.create(context, R.raw.speed_up);
            warmup = MediaPlayer.create(context, R.raw.warmup);
            sprint = MediaPlayer.create(context, R.raw.sprint);
            sprint2 = MediaPlayer.create(context, R.raw.sprint2);
            cooldown = MediaPlayer.create(context, R.raw.cooldown);
            cooldown2 = MediaPlayer.create(context, R.raw.cooldown2);
            end = MediaPlayer.create(context, R.raw.end);

            slow1 = MediaPlayer.create(context, R.raw.slow1);
            slow2 = MediaPlayer.create(context, R.raw.slow2);
            slow3 = MediaPlayer.create(context, R.raw.slow3);
            slow4 = MediaPlayer.create(context, R.raw.slow4);
            slow5 = MediaPlayer.create(context, R.raw.slow5);
            slow6 = MediaPlayer.create(context, R.raw.slow6);

            fast1 = MediaPlayer.create(context, R.raw.fast1);
            fast2 = MediaPlayer.create(context, R.raw.fast2);
            fast3 = MediaPlayer.create(context, R.raw.fast3);
            fast4 = MediaPlayer.create(context, R.raw.fast4);
            fast5 = MediaPlayer.create(context, R.raw.fast5);
            fast6 = MediaPlayer.create(context, R.raw.fast6);

            slow1.setLooping(true);
            slow2.setLooping(true);
            slow3.setLooping(true);
            slow4.setLooping(true);
            slow5.setLooping(true);
            slow6.setLooping(true);
            fast1.setLooping(true);
            fast2.setLooping(true);
            fast3.setLooping(true);
            fast4.setLooping(true);
            fast5.setLooping(true);
            fast6.setLooping(true);

            slow = new MediaPlayer[]{slow1, slow2, slow3, slow4, slow5, slow6};
            fast = new MediaPlayer[]{fast1 ,fast2, fast3, fast4, fast5, fast6};
        } catch (Exception e) {
            System.out.print("MediaPlayer error");
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void play(String message)

    {
        if (first) {
            setMusic();
            first = false;
        }

        int hr = 0;
        String temp = message.substring(4);  //Find only the hr in entire string

        try {
            hr = Integer.parseInt(temp);
            System.out.println(hr);
        } catch (NumberFormatException nfe) {
            System.out.println("Error processing hr");
        }

        switch(currentLevel) {

            case FIRST: //warmup interval
                if (!started) {
                    warmup.start();
                    final int rnd = new Random().nextInt(6);
                    holder = slow[rnd];
                    warmup.setNextMediaPlayer(holder);
                    int delay = 15000;
                    int period = 1000000;
                    timer.scheduleAtFixedRate(new TimerTask() {

                        public void run() {
                            holder.pause();
                            currentLevel = Level.SECOND;
                            started = false;
                            this.cancel();
                        }
                    }, delay, period);
                    started = true;
                }
                break;
            case SECOND: //speed interval
                if(!started) {
                    warned = true;
                    sprint.start();

                    int initDelay = 1000;
                    int initPeriod = 100000;
                    timer.scheduleAtFixedRate(new TimerTask() {

                        public void run() {
                            warned = false;
                            this.cancel();
                        }
                    }, initDelay, initPeriod);


                    final int rnd = new Random().nextInt(6);
                    holder = fast[rnd];
                    sprint.setNextMediaPlayer(holder);
                    int delay = 15000;
                    int period = 1000000;
                    timer.scheduleAtFixedRate(new TimerTask() {

                        public void run() {
                            holder.pause();
                            currentLevel = Level.THIRD;
                            started = false;
                            this.cancel();
                        }
                    }, delay, period);
                    started = true;
                }
                break;
            case THIRD: //recovery interval
                if(!started) {
                    warned = true;
                    cooldown.start();

                    int initDelay = 1000;
                    int initPeriod = 100000;
                    timer.scheduleAtFixedRate(new TimerTask() {

                        public void run() {
                            warned = false;
                            this.cancel();
                        }
                    }, initDelay, initPeriod);

                    final int rnd = new Random().nextInt(6);
                    holder = slow[rnd];
                    cooldown.setNextMediaPlayer(holder);
                    int delay = 15000;
                    int period = 1000000;
                    timer.scheduleAtFixedRate(new TimerTask() {

                        public void run() {
                            holder.pause();
                            currentLevel = Level.FOURTH;
                            started = false;
                            this.cancel();
                        }
                    }, delay, period);
                    started = true;
                }
                break;
            case FOURTH: //speed interval
                if(!started) {
                    warned = true;
                    sprint2.start();

                    int initDelay = 1000;
                    int initPeriod = 100000;
                    timer.scheduleAtFixedRate(new TimerTask() {

                        public void run() {
                            warned = false;
                            this.cancel();
                        }
                    }, initDelay, initPeriod);

                    final int rnd = new Random().nextInt(6);
                    holder = fast[rnd];
                    sprint2.setNextMediaPlayer(holder);
                    int delay = 15000;
                    int period = 1000000;
                    timer.scheduleAtFixedRate(new TimerTask() {

                        public void run() {
                            holder.pause();
                            currentLevel = Level.FIFTH;
                            started = false;
                            this.cancel();
                        }
                    }, delay, period);
                    started = true;
                }
                break;
            case FIFTH: //cooldown interval
                if(!started) {
                    warned = true;
                    cooldown2.start();

                    int initDelay = 1000;
                    int initPeriod = 100000;
                    timer.scheduleAtFixedRate(new TimerTask() {

                        public void run() {
                            warned = false;
                            this.cancel();
                        }
                    }, initDelay, initPeriod);

                    final int rnd = new Random().nextInt(6);
                    holder = slow[rnd];
                    cooldown2.setNextMediaPlayer(holder);
                    int delay = 15000;
                    int period = 1000000;
                    timer.scheduleAtFixedRate(new TimerTask() {

                        public void run() {
                            holder.pause();
                            currentLevel = Level.END;
                            started = false;
                            this.cancel();
                        }
                    }, delay, period);
                    started = true;
                }
                break;
            case END:
                if(!started) {
                    end.start();
                    started = true;
                }
                break;
        }



        // HR Monitoring
        switch (currentLevel) {
            case FIRST: //warmup
                if (hr > 100) {
                    if (!warned) {
                        tooFast.start();
                        int delay = 5000;
                        int period = 1000000;
                        timer.scheduleAtFixedRate(new TimerTask() {

                            public void run() {
                                warned = false;
                                this.cancel();
                            }
                        }, delay, period);
                        warned = true;
                    }

                }
                break;
            case SECOND: //speed
                if (hr < 90) {
                    if (!warned) {
                        holder.setVolume((float)0.5,(float)0.5);
                        tooSlow.start();
                        tooSlow.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                    holder.setVolume(1,1);

                            }
                        });

                        int delay = 5000;
                        int period = 1000000;
                        timer.scheduleAtFixedRate(new TimerTask() {

                            public void run() {
                                warned = false;
                                this.cancel();
                            }
                        }, delay, period);
                        warned = true;
                    }

                }
                break;
            case THIRD: //recovery
                if (hr > 90) {
                    if (!warned) {
                        holder.setVolume((float)0.5,(float)0.5);
                        tooFast.start();
                        tooFast.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                    holder.setVolume(1,1);

                            }
                        });
                        int delay = 5000;
                        int period = 1000000;
                        timer.scheduleAtFixedRate(new TimerTask() {

                            public void run() {
                                warned = false;
                                this.cancel();
                            }
                        }, delay, period);
                        warned = true;
                    }

                }
                break;
            case FOURTH: //speed
                if (hr < 90) {
                    if (!warned) {
                        holder.setVolume((float)0.5,(float)0.5);
                        tooSlow.start();
                        tooSlow.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                holder.setVolume(1,1);

                            }
                        });

                        int delay = 5000;
                        int period = 1000000;
                        timer.scheduleAtFixedRate(new TimerTask() {

                            public void run() {
                                warned = false;
                                this.cancel();
                            }
                        }, delay, period);
                        warned = true;
                    }

                }
                break;
            case FIFTH: //cooldown
                if (hr > 100) {
                    if (!warned) {
                        holder.setVolume((float)0.5,(float)0.5);
                        tooFast.start();
                        tooFast.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                holder.setVolume(1,1);
                            }
                        });
                        int delay = 5000;
                        int period = 1000000;
                        timer.scheduleAtFixedRate(new TimerTask() {

                            public void run() {
                                warned = false;
                                this.cancel();
                            }
                        }, delay, period);
                        warned = true;
                    }

                }
                break;

        } // switch



    } //play



}