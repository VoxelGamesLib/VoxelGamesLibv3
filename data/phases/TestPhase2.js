const PhaseBuilder = Java.type('com.voxelgameslib.game.builder.PhaseBuilder');
const Identifier = Java.type('com.voxelgameslib.util.Identifier');

const phase = PhaseBuilder.of(Identifier.ofVGL("TestPhase2"))
    .withFeature(Identifier.ofVGL("TestFeature"))
    //.withFeature(Identifier.ofVGL("TestFeature"), (feature) => feature.setTest("test"))
    .build();

phase; //return
