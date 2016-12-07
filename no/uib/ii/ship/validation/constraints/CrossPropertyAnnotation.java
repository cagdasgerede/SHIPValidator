/**
 *
 */
package no.uib.ii.ship.validation.constraints;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import no.uib.ii.ship.validation.ValidationException;
import no.uib.ii.ship.validation.annotations.cross.CrossValidation;
import no.uib.ii.ship.validation.annotations.cross.property.AllProperty;
import no.uib.ii.ship.validation.annotations.cross.property.CrossOperator;
import no.uib.ii.ship.validation.annotations.cross.property.CrossProperty;
import no.uib.ii.ship.validation.annotations.cross.property.ICrossOperator;
import no.uib.ii.ship.validation.summary.AllPropertySummary;
import no.uib.ii.ship.validation.summary.CrossPropertySummary;
import no.uib.ii.ship.validation.summary.ISummary;
import no.uib.ii.ship.validation.summary.AnnotationSummary;

/**
 * Represents @AllProperty, @CrossOperator and @CrossProperty, that is, cross-tests which are composed of property-tests
 * in one of three ways.The children AnnotationObjects of this object are PropertyAnnotations, while the "parent" is a CrossAnnotation, or this is the root.
 *
 * @author Dag Hovland and Federico Mancini
 *
 */
@SuppressWarnings("unchecked")
public class CrossPropertyAnnotation extends AnnotationObject<ICrossOperator,List<Object>,Object> {

	/**
	 * @param grandChildConstrs
	 * @param parent
	 */
	public CrossPropertyAnnotation(List<Annotation> grandChildConstrs,
			Annotation parent) {
		super(grandChildConstrs, "cross constraint", parent);
	}

	/*
	 * @see no.uib.ii.ship.validation.PropertyAnnotation#createChild(no.uib.ii.ship.validation.MetaConstraints, java.lang.annotation.Annotation)
	 */
	@Override
	public PropertyAnnotation createChild(MetaConstraints childConstrs, Annotation ann){
		List<Annotation> grandChildConstrs = childConstrs.getMethodConstraints();
		return new PropertyAnnotation(grandChildConstrs, "Composed cross-test", ann);
	}

	@Override
	AnnotationSummary runRecTest(List<Object> values) {

		List<ISummary> cr = new ArrayList<ISummary>();


		if (this.isAnnotationPresent(CrossOperator.class)){
			CrossOperator co = (CrossOperator) this.annotationType().getAnnotation(CrossOperator.class);
			Class<ICrossOperator> op = (Class<ICrossOperator>) co.value();
			ICrossOperator<?> summer_object=getTesterObject(op);
			Method m = callTest(summer_object, this.getInnerTesterName());
			assert(values.size() > 0);
			if(values.size() == 1){
				throw new ValidationException("CrossOperator cannot be used on only one property, as seen in " + this.getMethodName());
			}
			Iterator<Object> iter = values.iterator();
			assert(iter.hasNext());
			Object sum = iter.next();
			while(iter.hasNext()){
				Object value = iter.next();
				try {
					sum = m.invoke(summer_object, sum, value);
				} catch (IllegalArgumentException e) {
					throw new ValidationException("Could not sum list", e);
				} catch (IllegalAccessException e) {
					throw new ValidationException("Could not sum list", e);
				} catch (InvocationTargetException e) {
					throw new ValidationException("Could not sum list", e);
				}
			}
			values = new ArrayList<Object>();
			values.add(sum);
		}
		for(Object v : values){
			ISummary sn = testRunner(v);
			cr.add(sn);
		}
		AnnotationSummary annotationSummary;
		if(this.isAnnotationPresent(AllProperty.class)){
			annotationSummary = new AllPropertySummary(this.getSimpleName(), this.getMessage(), cr,  this.annotationType().getAnnotation(AllProperty.class));
		} else if(this.isAnnotationPresent(CrossProperty.class))
			annotationSummary = new CrossPropertySummary(this.getSimpleName(), this.getMessage(), cr, this.annotationType().getAnnotation(CrossProperty.class));
		else if (this.isAnnotationPresent(CrossOperator.class))
			annotationSummary = new AnnotationSummary(this.getSimpleName(), this.getMessage(), cr);
		else {
			assert(false);
			throw new ValidationException("Wrong annotation give to CrossPropertyAnnotation");
		}
		return annotationSummary;
	}

	/*
	 * @see no.uib.ii.ship.validation.AnnotationObject#getTestInterface()
	 */
	@Override
	public Class<ICrossOperator> getTestInterface() {
		return  ICrossOperator.class ;
	}


	/*
	 * @see no.uib.ii.ship.validation.AnnotationObject#getConstraintType()
	 */
	@Override
	Class<? extends Annotation> getConstraintType() {
		return CrossValidation.class;
	}

	/*
	 * @see no.uib.ii.ship.validation.AnnotationObject#getInnerTesterName()
	 */
	@Override
	public String getInnerTesterName() {
		return "op";
	}

}
