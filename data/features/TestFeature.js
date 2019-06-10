const ScriptFeature = Java.type('com.voxelgameslib.game.scriptresolver.ScriptFeature');
const Identifier = Java.type('com.voxelgameslib.util.Identifier');


const testfeature = new ScriptFeature(Identifier.ofVGL("TestFeature"));
testfeature.init(() => {
    testfeature.getGameInstance().getEventBus();
    print("init!");
});

testfeature; // return
