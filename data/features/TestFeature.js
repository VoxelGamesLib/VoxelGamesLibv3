const ScriptFeature = Java.type('com.voxelgameslib.game.scriptresolver.ScriptFeature');
const Identifier = Java.type('com.voxelgameslib.util.Identifier');

const FeatureStartEvent = Java.type('com.voxelgameslib.game.events.FeatureStartEvent');


const testfeature = new ScriptFeature(Identifier.ofVGL("TestFeature"));
testfeature.init(() => {
    const eventBus = testfeature.getGameInstance().getEventBus();
    eventBus.subscribe(FeatureStartEvent.class, () => print("Start!"));
    print("init!");
});

testfeature; // return
