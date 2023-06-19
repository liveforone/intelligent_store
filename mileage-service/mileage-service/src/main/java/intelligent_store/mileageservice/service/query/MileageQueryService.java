package intelligent_store.mileageservice.service.query;

import intelligent_store.mileageservice.repository.MileageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MileageQueryService {

    private final MileageRepository mileageRepository;


}
