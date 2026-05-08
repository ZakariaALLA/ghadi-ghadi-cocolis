import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { TripService } from '../../services/trip.service';

@Component({
  selector: 'app-publish-trajet',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './publish-trajet.component.html',
  styleUrl: './publish-trajet.component.css'
})
export class PublishTrajetComponent {
  tripForm: FormGroup;
  isSubmitted = false;

  constructor(
    private fb: FormBuilder,
    private tripService: TripService,
    private router: Router
  ) {
    this.tripForm = this.fb.group({
      departureCity: ['', Validators.required],
      arrivalCity: ['', Validators.required],
      tripDate: ['', Validators.required]
    });
  }

  onSubmit() {
    this.isSubmitted = true;
    if (this.tripForm.valid) {
      this.tripService.publishTrip(this.tripForm.value).subscribe({
        next: () => {
          alert('Trajet publié avec succès !');
          this.router.navigate(['/']);
        },
        error: (err) => alert('Erreur lors de la publication')
      });
    }
  }
}
