<?php

namespace SocialProBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Jobs
 *
 * @ORM\Table(name="jobs", uniqueConstraints={@ORM\UniqueConstraint(name="job_desc", columns={"job_desc"}), @ORM\UniqueConstraint(name="job_id", columns={"job_id"})})
 * @ORM\Entity
 */
class Jobs
{
    /**
     * @var integer
     *
     * @ORM\Column(name="job_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $jobId;

    /**
     * @var string
     *
     * @ORM\Column(name="job_desc", type="string", length=20, nullable=false)
     */
    private $jobDesc;


}

