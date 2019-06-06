var GameTypeBuilder = Java.type('com.voxelgameslib.game.builder.GameTypeBuilder');
var Identifier = Java.type('com.voxelgameslib.util.Identifier');

var type = GameTypeBuilder.of(Identifier.ofVGL("TestGameType"))
    .withNewPhase(Identifier.ofVGL("TestPhase"))
    //.withFeature(TestFeature.class)
    //.withFeature(TestFeature.class, (feature) - > feature.setTest("test"))
    .build()
    .build();
print(type);
