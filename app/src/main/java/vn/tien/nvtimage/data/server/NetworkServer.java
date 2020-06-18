package vn.tien.nvtimage.data.server;

import android.content.Context;

public class NetworkServer {
    private static DataServer sDataServer;

    public static DataServer getDataServer(Context context){
        if (sDataServer == null){
            sDataServer = PhotoClient.getInstance(context).create(DataServer.class);
        }
        return sDataServer;
    }
}
