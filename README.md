<h5>Original Repository </h5>
https://github.com/hirish99/Frogger-Arcade-Game

<h5>Changes Made</h5>
<h6>Integrating Gradle</h6>
Gradle build files were added to the project to manage dependencies such as the JavaFX library.
<h6>Separating Files</h6>
The original files were separated into different directories. Associated files were grouped together. 
<h6>Resource Management</h6>
The original code used hardcoded Strings for paths to image resource files every time it created Actor objects. 
This was changed to String constants which were referenced if needed. Each Actor subclass has a string constant
representing the path to its image resource file. This was done so that if the paths were to change, only the constant
requires changing. This also means that Main.java no longer needed to pass in a path as a parameter every time 
it created Actor objects.
<h6>Using Enums and Variables</h6>
The original code used hardcoded arbitrary numbers for level generation, collision checking and out of bounds checking.
New code stored these values into variables when necessary. Y-coordinates for level generation were stored inside an
enumeration with y-coordinates for each of the 15 rows of the game. New levels can just use this enumeration for generation
rather than trial and error with arbitrary numbers.
<h6>More Memory Efficient</h6>
The original code instanced new JavaFX Images for every single Actor created. This was inefficient as new Images were 
just using the same image resource. The new code loads each image resource file into a static list of 
Images(stored in respective subclasses of Actor), so only one instance of Image is created for each image resource file.
<h6>Simplified Logic within Classes</h6>
Many if-else statements were converted to switch statements if necessary for better readability. Duplicate code was 
abstracted into separate methods for easier modification. Animal class collision checking was heavily modified 
to check for collision between the Animal instance and Obstacle and Platform class. This supports the Open-Closed 
Principle since the addition of new subclasses of Platform and Obstacle do not require modifying code in the Animal 
class. Directional assets were removed and instead, image direction is based off the speed of the instance.
JavaBeans PropertyChangeListeners were used instead of boolean values to implement the Observer design pattern.
<h6>Added Level class and Navigation Component</h6>
A Level abstract class was added as the base class for all custom Level classes. Main.java no longer adds child nodes
to a root node. Rather, this is all done in the constructor of the Level class and its subclasses. This was done to 
assign separate functionality to different classes. A LevelFactory class was added for instantiating custom Levels. 
Other screens such as the MainMenu and InfoPage class were also added. To handle screen switching, a Navigation and 
NavController class was added. NavControllers are associated with a JavaFXScene, and calling Scene.setRoot() 
within the NavController was used for switching between screens. This supports the MVC principle.
<h6>Highscore Functionality</h6>
Highscores for each level is now stored in separate csv files. HighscoreLoader added for reading and writing while
HighscoreController is used to handle the logic behind managing highscores.

<h5>Credits</h5>
Racecar sprite: 
http://www.classicgaming.cc/classics/frogger/icons

Fireball sprites:
https://kvsr.itch.io/pixelarteffectfx017