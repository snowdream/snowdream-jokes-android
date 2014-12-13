/*
 * Copyright (C) 2014 Snowdream Mobile <yanghui1986527@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.snowdream.android.apps.jokes;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import org.parceler.Parcels;

import java.util.List;

public class MainActivity extends ActionBarActivity {
    private  GoogleCardsFragment googleCardsFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();
    }

    private void setData() {
        JokeManager.getJokes(new CallBack() {
            @Override
            public void callback(List<Joke> jokes) {
                if (jokes != null) {
                    googleCardsFragment = new GoogleCardsFragment();

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("jokes",Parcels.wrap(jokes));
                    googleCardsFragment.setArguments(bundle);

                    //if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.container, googleCardsFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                    // }
                }
            }
        });
    }
 }
