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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import org.parceler.Parcels;

import java.util.List;

/**
 * Created by hui.yang on 2014/12/14.
 */
public class GoogleCardsFragment extends Fragment {
    private GoogleCardsAdapter mGoogleCardsAdapter = null;
    private  ListView listView = null;

    public GoogleCardsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_google_cards, container, false);

        listView = (ListView) rootView.findViewById(R.id.listview);

        Bundle bundle = getArguments();
        if (bundle == null){
            return rootView;
        }

        List<Joke> jokes = Parcels.unwrap(bundle.getParcelable("jokes")) ;
        mGoogleCardsAdapter = new GoogleCardsAdapter(getActivity());
        mGoogleCardsAdapter.addAll(jokes);
        listView.setAdapter(mGoogleCardsAdapter);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
