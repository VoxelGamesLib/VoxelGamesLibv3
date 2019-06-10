const GameTypeBuilder = Java.type('com.voxelgameslib.game.builder.GameTypeBuilder');
const PhaseBuilder = Java.type('com.voxelgameslib.game.builder.PhaseBuilder');
const Identifier = Java.type('com.voxelgameslib.util.Identifier');

const type = GameTypeBuilder.of(Identifier.ofVGL("TestGameType"))
    .withPhase(
        PhaseBuilder.of(Identifier.ofVGL("TestPhase"))
            .withFeature(Identifier.ofVGL("TestFeature"))
            //.withFeature(Identifier.ofVGL("TestFeature"), (feature) => feature.setTest("test"))
            .build()
    )
    .withPhase(Identifier.ofVGL("TestPhase2"))
    .build();

type; // return

