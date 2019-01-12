package br.com.swchallenge.api.events;


import br.com.swchallenge.api.model.Planet;
import br.com.swchallenge.api.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class PlanetModelListener extends AbstractMongoEventListener<Planet> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public PlanetModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Planet> event) {
        event.getSource().setId(sequenceGenerator.generateSequence(Planet.SEQUENCE_NAME));
    }


}
