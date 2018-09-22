Alex Nelson
CSC 133
Assignment 1

Code Sumbission:

I included both a .pdf and a .png version of my UML diagram because the .pdf version looked like it was broken up into separate pages.

As far as notes on my code:
	I decided to implement toString at each level of my GameObjects. Meaning that each time an object is asked to print out
		it will first call its super.toString() method and will add any information that is relevant that is contains. For
		example, when printing a SapceStation.toString() it will print its name, blinkRate, and lightStatus after calling 
		super.toString() which will have FixedObject print its toString() which then calls GameObjects.toString(), etc.

	I created a composition relationship between MissileLaunchers and their respective (Non)PlayerShip objects by instantiating
		a private (Steerable)MissileLauncher whenever a new ship object is created. This allows for ease of keeping those two
		objects at the same location while allowing them to be separate objects with seperate headings. Although after asking
		our Professor who said it was not required at this point, SteerableMissileLauncher Objects have their own toString() methods 
		which allowed me to make sure they could change their headings dependently while staying at the same location as the
		Player Ship.