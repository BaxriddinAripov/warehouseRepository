package uz.pdp.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.Measurement;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.MeasurementRepository;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    final
    MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    // CREAT
    public Result addMeasurement(Measurement measurement){
        boolean exists = measurementRepository.existsByName(measurement.getName());
        if (exists) {
            return new Result("Bunday " + measurement.getName() + " nomli o'lchov birligi mavjud", false);
        }
        Measurement measurement1 = new Measurement();
        measurement1.setName(measurement.getName());
        measurementRepository.save(measurement1);
        return new Result("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    // GET ALL
    public List<Measurement> allMeasurement(){
        return measurementRepository.findAll();
    }

    // GET BY UI
    public Measurement getMeasurementById(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.orElse(null);
    }

    // DELETE
    public Result deleteMeasurementById(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o'chov birligi mavjud emas", false);
        measurementRepository.deleteById(id);
        return new Result("Ma'lumot muvaffaqiyatli o'chirildi", true);
    }

}
