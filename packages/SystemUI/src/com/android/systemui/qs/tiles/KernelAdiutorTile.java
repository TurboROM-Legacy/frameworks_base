/*
 * Copyright (C) 2014 The Android Open Source Project
 * Copyright (C) 2012-2015 The CyanogenMod Project
 * Copyright 2014-2015 The Euphoria-OS Project
 * Copyright 2016 Benzo Rom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.qs.tiles;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.UserHandle;
import android.os.Handler;
import android.os.RemoteException;
import android.view.View;

import com.android.internal.logging.MetricsLogger;
import com.android.systemui.R;
import com.android.systemui.qs.QSTile;

/** Quick settings tile: KernelAdiutor **/
public class KernelAdiutorTile extends QSTile<QSTile.BooleanState> {

    private static final String CATEGORY_KERNEL_ADIUTOR = "com.grarak.kerneladiutor.MainActivity";
    private boolean mListening;

    public KernelAdiutorTile(Host host) {
        super(host);
    }

    @Override
    protected BooleanState newTileState() {
        return new BooleanState();
    }

    public void setListening(boolean listening) {
    }

    @Override
    public void handleClick() {
        MetricsLogger.action(mContext, getMetricsCategory());
        mHost.collapsePanels();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.grarak.kerneladiutor",
            "com.grarak.kerneladiutor.MainActivity");
        mHost.startActivityDismissingKeyguard(intent);
    }

    @Override
    public void handleLongClick() {
    }

    @Override
    public int getMetricsCategory() {
        return MetricsLogger.QS_KERNEL;
    }

    @Override
    protected void handleUpdateState(BooleanState state, Object arg) {
        state.visible = true;
        state.label = mContext.getString(R.string.quick_settings_kernel_adiutor_label);
        state.contentDescription = mContext.getString(
                R.string.accessibility_quick_settings_kernel_adiutor);
        state.icon = ResourceIcon.get(R.drawable.ic_qs_kernel);
    }
}
