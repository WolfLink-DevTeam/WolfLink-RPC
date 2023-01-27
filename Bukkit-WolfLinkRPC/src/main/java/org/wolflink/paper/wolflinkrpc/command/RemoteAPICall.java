package org.wolflink.paper.wolflinkrpc.command;

import org.jetbrains.annotations.NotNull;
import org.wolflink.common.wolflinkrpc.api.annotations.LocalCallHandler;
import org.wolflink.common.wolflinkrpc.api.interfaces.CallbackFunction;
import org.wolflink.common.wolflinkrpc.entity.RPCDataPack;
import org.wolflink.common.wolflinkrpc.entity.impl.handler.local.RemoteAPICallImpl;
import org.wolflink.paper.wolflinkrpc.App;

@LocalCallHandler
public class RemoteAPICall extends RemoteAPICallImpl {
    public RemoteAPICall() {
        super(new CallbackFunction() {
            @Override
            public void success(@NotNull RPCDataPack datapack) {
                String info = datapack.getJsonObject().get("info").getAsString();
                App.RPC_CONFIGURATION.getLogger().info(info);
            }

            @Override
            public void failed(@NotNull RPCDataPack datapack) {
                CallbackFunction.DefaultImpls.failed(this,datapack);
            }
        });
    }
}
