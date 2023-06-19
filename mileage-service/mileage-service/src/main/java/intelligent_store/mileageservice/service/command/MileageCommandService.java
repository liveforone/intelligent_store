package intelligent_store.mileageservice.service.command;

import intelligent_store.mileageservice.repository.MileageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MileageCommandService {

    private final MileageRepository mileageRepository;
}
