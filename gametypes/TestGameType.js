const GameTypeBuilder = Java.type('com.voxelgameslib.game.builder.GameTypeBuilder');
const Identifier = Java.type('com.voxelgameslib.util.Identifier');

const type = GameTypeBuilder.of(Identifier.ofVGL("TestGameType"))
    .withNewPhase(Identifier.ofVGL("TestPhase"))
        .withFeature(Java.type('com.voxelgameslib.voxelgameslib.test.TestFeature').class)
        .withFeature(Java.type('com.voxelgameslib.voxelgameslib.test.TestFeature').class, (feature) => feature.setTest("test"))
        .build()
    .build();

type; // return
