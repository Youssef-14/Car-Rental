import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { StorageService } from '../../auth/components/services/storage/storage.service';

export const customerGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  if (StorageService.isCustomerLoggedIn()) {
    return true;
  } else if (StorageService.isLoggedIn()) {
    router.navigate(['/dashboard']);
    return false;
  }else {
    router.navigate(['/login']);
    return false;
  }
};

