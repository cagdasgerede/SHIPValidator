Fork of https://sourceforge.net/projects/shipvalidator


------------------------------------------
               Version 0.3
-------------------------------------------

Additions:

- More javadoc.
- Renamed some classes
- Fixed a bug in MetaConstraints
- Added diagrams to tex/tecrep/fig


-------------------------------------------
               Version 0.2
-------------------------------------------

Additions:

- Support for composing Test-Annotations into Cross-Annotations (see annotations in the package no.uib.ii.ship.annotations.cross.property for some examples)
- Refactoring of the code by usage of the composite and strategy patterns.
- Added a Junit test folder (test).
--------------------------------------------
                     Usage
--------------------------------------------

1) Add the SHIPValidator.jar to your classpath
2) Test whether it works by using the following command:
   java no.uib.ii.ship.validation.test.Webform

3) Add the following code to your application to use the validator:

   IValidatorFactory vf = new ValidatorFactory(); //Instantiate a validator factory
   Validator val = vf.getValidator(); // Create a validator
   ValidationSummary vs = val.validate(w); // validate the annotated object w
   System.out.println(vs.toString()); // See the outcome of the test
 (notice that "val" can be reused without having to instantiate a new validator)

In the folder tex is possible to find the article describing the framework which can be used as a first draft of the manual.


For questions or comments contact:
dag.hovland@uib.no
federico.mancini@uib.no
