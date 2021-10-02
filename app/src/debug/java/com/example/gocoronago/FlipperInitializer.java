package com.example.gocoronago;

import android.content.Context;

import com.facebook.flipper.android.AndroidFlipperClient;
import com.facebook.flipper.android.utils.FlipperUtils;
import com.facebook.flipper.core.FlipperClient;
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin;
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin;
import com.facebook.flipper.plugins.inspector.DescriptorMapping;
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin;
import com.facebook.flipper.plugins.leakcanary2.FlipperLeakListener;
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin;
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin;
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor;
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin;
import com.facebook.soloader.SoLoader;

import leakcanary.LeakCanary;
import okhttp3.Interceptor;

public final class FlipperInitializer {

    public static Interceptor initFlipperPlugins(Context context) {
        SoLoader.init(context, false);
        final FlipperClient client = AndroidFlipperClient.getInstance(context);
        final NetworkFlipperPlugin networkPlugin = new NetworkFlipperPlugin();
        final FlipperOkhttpInterceptor interceptor =
                new FlipperOkhttpInterceptor(networkPlugin, true);
        LeakCanary.Config config =
                LeakCanary.getConfig()
                        .newBuilder()
                        .onHeapAnalyzedListener(new FlipperLeakListener())
                        .build();
        LeakCanary.setConfig(config);
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(context)) {
            final DescriptorMapping descriptorMapping = DescriptorMapping.withDefaults();
            client.addPlugin(new InspectorFlipperPlugin(context, descriptorMapping));
            client.addPlugin(networkPlugin);

            client.addPlugin(new DatabasesFlipperPlugin(context));
            client.addPlugin(CrashReporterPlugin.getInstance());
            client.addPlugin(new LeakCanary2FlipperPlugin());
            client.addPlugin(NavigationFlipperPlugin.getInstance());
            client.start();
        }
        return interceptor;
    }
}
